package io.github.thefishlive.crash;

import io.github.thefishlive.format.Format;

public interface CrashReportSection {

    public String build(Format format);

    public String getName();

}
