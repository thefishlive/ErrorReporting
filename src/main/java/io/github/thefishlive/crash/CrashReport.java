package io.github.thefishlive.crash;

import io.github.thefishlive.crash.sections.EnvironmentSection;
import io.github.thefishlive.crash.sections.ExceptionSection;
import io.github.thefishlive.format.Format;
import io.github.thefishlive.upload.UploadTarget;
import io.github.thefishlive.upload.UploadTargets;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CrashReport {

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

    public String upload(UploadTargets target) throws IOException,ReflectiveOperationException {
        UploadTarget handler = target.newHandler();
        return handler.upload(this);
    }

    public String build(Format format) {
        StringBuilder builder = new StringBuilder();
        builder.append(format.header(1)).append("Crash Report").append(format.newLine());
        builder.append("I'm so sorry, it looks like you have crashed.").append(format.paragraphBreak());
        builder.append(format.bold()).append("Description:").append(format.bold()).append(" ").append(getDescription()).append(format.paragraphBreak());

        for (CrashReportSection section : getSections()) {
            builder.append(format.header(2)).append(section.getName()).append(format.newLine());
            builder.append(section.build(format));
            builder.append(format.paragraphBreak());
        }

        return builder.toString();
    }

    public Date getCrashDate() {
        return this.crashDate;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String getDescription() {
        return this.description;
    }

    public List<CrashReportSection> getSections() {
        return this.sections;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSections(List<CrashReportSection> sections) {
        this.sections = sections;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CrashReport)) return false;
        final CrashReport other = (CrashReport) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$crashDate = this.crashDate;
        final Object other$crashDate = other.crashDate;
        if (this$crashDate == null ? other$crashDate != null : !this$crashDate.equals(other$crashDate)) return false;
        final Object this$throwable = this.throwable;
        final Object other$throwable = other.throwable;
        if (this$throwable == null ? other$throwable != null : !this$throwable.equals(other$throwable)) return false;
        final Object this$description = this.description;
        final Object other$description = other.description;
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$sections = this.sections;
        final Object other$sections = other.sections;
        if (this$sections == null ? other$sections != null : !this$sections.equals(other$sections)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $crashDate = this.crashDate;
        result = result * PRIME + ($crashDate == null ? 0 : $crashDate.hashCode());
        final Object $throwable = this.throwable;
        result = result * PRIME + ($throwable == null ? 0 : $throwable.hashCode());
        final Object $description = this.description;
        result = result * PRIME + ($description == null ? 0 : $description.hashCode());
        final Object $sections = this.sections;
        result = result * PRIME + ($sections == null ? 0 : $sections.hashCode());
        return result;
    }

    public boolean canEqual(Object other) {
        return other instanceof CrashReport;
    }

    public String toString() {
        return "io.github.thefishlive.crash.CrashReport(crashDate=" + this.getCrashDate() + ", throwable=" + this.getThrowable() + ", description=" + this.getDescription() + ", sections=" + this.getSections() + ")";
    }
}
