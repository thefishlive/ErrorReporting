package io.github.thefishlive.upload.gist;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@RequiredArgsConstructor
public class GistData {

    private String description;
    @SerializedName("public") private boolean access;
    private Map<String, GistFile> files = new HashMap<>();

    public void addFile(String string, GistFile file) {
        files.put(string, file);
    }

    @Data
    @ToString
    @RequiredArgsConstructor
    public static class GistFile {
        private final String content;
    }
}
