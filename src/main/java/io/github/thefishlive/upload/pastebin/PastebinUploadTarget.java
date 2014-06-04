package io.github.thefishlive.upload.pastebin;

import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.format.DefaultUploadFormat;
import io.github.thefishlive.format.Format;
import io.github.thefishlive.upload.UploadTarget;
import io.github.thefishlive.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

public class PastebinUploadTarget implements UploadTarget {

    private static final Logger logger = LogManager.getLogger();
    
    private static final String PASTEBIN_BASE_URL = "http://pastebin.com/api/";
    private static final URL PASTEBIN_POST_URL = Utils.constantUrl(PASTEBIN_BASE_URL + "api_post.php");

    private static final String API_DEV_KEY = "afbad7479d7048a216170991b84cce6c";
    private static final String CHARSET = "UTF-8";

    @Override
    public String upload(CrashReport report) {

        try {
            logger.debug(PASTEBIN_POST_URL);
            HttpURLConnection connection = (HttpURLConnection) PASTEBIN_POST_URL.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("GET");
            
            try (OutputStream output = connection.getOutputStream()) {
                String content = URLEncoder.encode(report.build(getFormat()), CHARSET);
                String title = URLEncoder.encode(Utils.formatDate(report.getCrashDate()), CHARSET);

                String data = "api_option=paste&api_dev_key=" + API_DEV_KEY +
                                "&api_paste_code=" + content +
                                "&api_paste_private=1" +
                                "&api_paste_name=" + title;

                output.write(data.getBytes(CHARSET));
            }

            connection.connect();

            try(InputStream input = connection.getInputStream()) {
                byte[] bytes = Utils.readFully(input);

                logger.debug(Arrays.toString(bytes));
                String data = new String(bytes, CHARSET);
                logger.debug(data);

                if (data.startsWith("http://")) {
                    return data;
                } else {
                    throw new IOException(data);
                }
            }
        } catch (IOException e) {
            logger.error("There was a error uploading crash report to pastebin", e);
        }

        return null;
    }

    @Override
    public Format getFormat() {
        return new DefaultUploadFormat();
    }

}
