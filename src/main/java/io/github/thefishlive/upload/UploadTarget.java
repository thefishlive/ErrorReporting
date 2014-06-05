package io.github.thefishlive.upload;

import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.format.Format;

import java.io.IOException;

public interface UploadTarget {

    String upload(CrashReport report) throws IOException;

    Format getFormat();

}
