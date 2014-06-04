package io.github.thefishlive.crash.sections;

import io.github.thefishlive.crash.CrashReportSection;
import io.github.thefishlive.format.Format;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionSection implements CrashReportSection {
    private final Throwable throwable;

    public ExceptionSection(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String build(Format format) {
        StringWriter builder = new StringWriter();
        builder.append(format.header(4)).append("Exception:").append(format.newLine());
        builder.append(format.list()).append(format.bold()).append("Exception:").append(format.bold()).append(" ").append(this.throwable.getClass().getName()).append(format.newLine());
        builder.append(format.list()).append(format.bold()).append("Message:").append(format.bold()).append(" ").append(this.throwable.getMessage()).append(format.paragraphBreak());

        Throwable cause = getCause();

        if (this.throwable != cause) {
            builder.append(format.header(4)).append("Cause:").append(format.newLine());
            builder.append(format.list()).append(format.bold()).append("Cause:").append(format.bold()).append(" ").append(cause.getClass().getName()).append(format.newLine());
            builder.append(format.list()).append(format.bold()).append("Message:").append(format.bold()).append(" ").append(cause.getMessage()).append(format.paragraphBreak());
        }

        builder.append(format.header(4)).append("Stacktrace:").append(format.code());
        this.throwable.printStackTrace(new PrintWriter(builder));
        builder.append(format.code());

        return builder.toString();
    }

    @Override
    public String getName() {
        return "Crash";
    }

    public Throwable getCause() {
        Throwable cause = this.throwable;

        if (this.throwable.getCause() == null) {
            return cause;
        }

        do {
            cause = this.throwable.getCause();
        } while(cause != null);

        return cause;
    }
}
