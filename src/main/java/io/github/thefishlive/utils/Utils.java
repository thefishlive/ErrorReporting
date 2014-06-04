package io.github.thefishlive.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Utils {

    private Utils() {}

    public static URL constantUrl(String url) {
        try {
            return URI.create(url).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
