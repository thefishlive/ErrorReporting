package io.github.thefishlive.crash;

import io.github.thefishlive.crash.sections.EnvironmentSection;
import io.github.thefishlive.crash.sections.ExceptionSection;
import io.github.thefishlive.format.Format;
import io.github.thefishlive.upload.UploadTarget;
import io.github.thefishlive.upload.UploadTargets;
import lombok.Data;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString
public class CrashReport {

    private static final Logger logger = LogManager.getLogger();

    private final Date crashDate;
    private Throwable throwable;
    private String description;
    private List<CrashReportSection> sections;

    public CrashReport(String description, Throwable throwable) {
        this.crashDate = new Date();
        this.description = description;
        this.throwable = throwable;
        this.sections = new LinkedList<>();

        addCrashReportSection(new ExceptionSection(throwable));
        addCrashReportSection(new EnvironmentSection());
    }

    public void addCrashReportSection(CrashReportSection section) {
        sections.add(section);
    }

    public String upload(UploadTargets target) {
        try {
            UploadTarget handler = target.newHandler();
            return handler.upload(this);
        } catch (ReflectiveOperationException e) {
            logger.error("Could not create upload handler for {}.", target.name().toLowerCase(), e);
        }

        return null;
    }

    public String build(Format format) {
        StringBuilder builder = new StringBuilder();
        builder.append(format.header(1)).append("Crash Report").append(format.newLine());
        builder.append("I'm so sorry, it looks like you have crashed.").append(format.paragraphBreak());
        builder.append(format.bold()).append("Description:").append(format.bold()).append(" ").append(getDescription()).append(format.paragraphBreak());

        for (CrashReportSection section : getSections()) {
            builder.append(format.header(2)).append(section.getName()).append(format.newLine());
            builder.append(section.build(format));
        }

        return builder.toString();
    }
}
