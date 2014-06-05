package io.github.thefishlive.upload.pastebin;

import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.upload.UploadTargets;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class TestPastebinUploadTarget {

    @Test
    public void testUpload() throws IOException, ReflectiveOperationException {
        CrashReport report = new CrashReport("Test crash report", new RuntimeException("Test crash"));
        String url = report.upload(UploadTargets.PASTEBIN);
        System.out.println(url);
        assertNotNull(url);
    }
}
