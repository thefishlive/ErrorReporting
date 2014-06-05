package io.github.thefishlive.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private Utils() {}

    public static URL constantUrl(String url) {
        try {
            return URI.create(url).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy:MM:dd-kk:mm:ss").format(date);
    }

    public static byte[] readFully(InputStream stream) throws IOException {
        byte[] data = new byte[4096];
        ByteArrayOutputStream entryBuffer = new ByteArrayOutputStream();
        int len;

        do {
            len = stream.read(data);
            if (len > 0) {
                entryBuffer.write(data, 0, len);
            }
        } while (len != -1);

        return entryBuffer.toByteArray();
    }

}
