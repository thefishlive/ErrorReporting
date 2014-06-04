package io.github.thefishlive.crash.sections;

import io.github.thefishlive.crash.CrashReportSection;
import io.github.thefishlive.format.Format;

public class EnvironmentSection implements CrashReportSection {
    @Override
    public String build(Format format) {
        StringBuilder builder = new StringBuilder();
        builder.append(format.list()).append(format.bold()).append("Operating System:").append(format.bold()).append(" ");
        builder.append(System.getProperty("os.name")).append(" (").append(System.getProperty("os.version")).append(")").append(format.newLine());

        builder.append(format.list()).append(format.bold()).append("OS Architecture:").append(format.bold()).append(" ");
        builder.append(System.getProperty("os.arch")).append(" (").append(System.getProperty("sun.arch.data.model")).append(")").append(format.newLine());

        builder.append(format.list()).append(format.bold()).append("Java Vendor:").append(format.bold()).append(" ");
        builder.append(System.getProperty("java.vendor")).append(" (").append(System.getProperty("java.vendor.url")).append(")").append(format.newLine());

        builder.append(format.list()).append(format.bold()).append("Java Version:").append(format.bold()).append(" ");
        builder.append(System.getProperty("java.version")).append(format.newLine());

        builder.append(format.list()).append(format.bold()).append("Java Home:").append(format.bold()).append(" ");
        builder.append(System.getProperty("java.home")).append(format.newLine());
        return builder.toString();
    }

    @Override
    public String getName() {
        return "Environment";
    }
}
