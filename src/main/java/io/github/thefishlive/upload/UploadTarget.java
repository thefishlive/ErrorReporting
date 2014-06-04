package io.github.thefishlive.upload;

import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.format.Format;

public interface UploadTarget {

    String upload(CrashReport report);

    Format getFormat();

}
