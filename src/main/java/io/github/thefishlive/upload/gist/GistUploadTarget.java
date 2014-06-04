package io.github.thefishlive.upload.gist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.thefishlive.crash.CrashReport;
import io.github.thefishlive.format.Format;
import io.github.thefishlive.upload.UploadTarget;
import io.github.thefishlive.upload.gist.GistData.GistFile;
import io.github.thefishlive.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class GistUploadTarget implements UploadTarget {

    private static final Logger logger = LogManager.getLogger();
    private static final String GITHUB_API_BASE = "https://api.github.com/";
    private static final URL GIST_UPLOAD_TARGET = Utils.constantUrl(GITHUB_API_BASE + "gists");

    private static final JsonParser parser = new JsonParser();
    private static final Gson GSON = new Gson();

    @Override
    public String upload(CrashReport report) {
        GistData data = new GistData();
        data.setDescription(report.getDescription());
        data.addFile(Utils.formatDate(report.getCrashDate()) + ".md", new GistFile(report.build(getFormat())));
        
        try {
            logger.debug(GIST_UPLOAD_TARGET.toExternalForm());
            HttpURLConnection connection = (HttpURLConnection) GIST_UPLOAD_TARGET.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            connection.setRequestMethod("POST");

            try (OutputStream output = connection.getOutputStream()) {
                String json = GSON.toJson(data);
                output.write(json.getBytes(Charset.forName("UTF-8")));
            }

            if (connection.getResponseCode() != 201) {
                throw new IOException("Server responded with invalid status code (Got " + connection.getResponseCode() + " Expected 201)");
            }

            try (InputStream stream = connection.getInputStream()) {
                JsonObject response = parser.parse(new InputStreamReader(stream)).getAsJsonObject();
                return response.get("html_url").getAsString();
            }

        } catch (IOException e) {
            logger.warn("A error has occurred whist uploading crash report to github.", e);
        }

        return null;
    }

    @Override
    public Format getFormat() {
        return new GistUploadFormat();
    }
}
