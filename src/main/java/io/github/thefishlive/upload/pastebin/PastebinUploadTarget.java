package io.github.thefishlive.upload.pastebin;

import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.format.DefaultUploadFormat;
import io.github.thefishlive.format.Format;
import io.github.thefishlive.upload.UploadTarget;
import io.github.thefishlive.utils.Utils;

import java.net.URL;

public class PastebinUploadTarget implements UploadTarget {

    private static String PASTEBIN_BASE_URL = "http://pastebin.com/api/";
    private static URL PASTEBIN_POST_URL = Utils.constantUrl(PASTEBIN_BASE_URL + "api_post.php");

    @Override
    public String upload(CrashReport report) {
        return null;
    }

    @Override
    public Format getFormat() {
        return new DefaultUploadFormat();
    }

}
