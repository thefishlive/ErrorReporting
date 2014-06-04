package io.github.thefishlive.upload.pastebin;

import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.upload.UploadTargets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestPastebinUploadTarget {

    private static final Logger logger = LogManager.getLogger();

    @Test
    public void testUpload() {
        CrashReport report = new CrashReport("Test crash report", new RuntimeException("Test crash"));
        String url = report.upload(UploadTargets.PASTEBIN);
        logger.debug(url);
        assertNotNull(logger);
    }
}
