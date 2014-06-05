package io.github.thefishlive.upload.gist;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class GistData {

    private String description;
    @SerializedName("public") private boolean access;
    private Map<String, GistFile> files = new HashMap<>();

    public GistData() {
    }

    public void addFile(String string, GistFile file) {
        files.put(string, file);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isAccess() {
        return this.access;
    }

    public Map<String, GistFile> getFiles() {
        return this.files;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public void setFiles(Map<String, GistFile> files) {
        this.files = files;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof GistData)) return false;
        final GistData other = (GistData) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$description = this.description;
        final Object other$description = other.description;
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.access != other.access) return false;
        final Object this$files = this.files;
        final Object other$files = other.files;
        if (this$files == null ? other$files != null : !this$files.equals(other$files)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $description = this.description;
        result = result * PRIME + ($description == null ? 0 : $description.hashCode());
        result = ((result * PRIME) + (this.access ? 79 : 97));
        final Object $files = this.files;
        result = result * PRIME + ($files == null ? 0 : $files.hashCode());
        return result;
    }

    public boolean canEqual(Object other) {
        return other instanceof GistData;
    }

    public String toString() {
        return "io.github.thefishlive.upload.gist.GistData(description=" + this.getDescription() + ", access=" + this.isAccess() + ", files=" + this.getFiles() + ")";
    }

    public static class GistFile {
        private final String content;

        @java.beans.ConstructorProperties({"content"})
        public GistFile(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof GistFile)) return false;
            final GistFile other = (GistFile) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$content = this.content;
            final Object other$content = other.content;
            if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $content = this.content;
            result = result * PRIME + ($content == null ? 0 : $content.hashCode());
            return result;
        }

        public boolean canEqual(Object other) {
            return other instanceof GistFile;
        }

        public String toString() {
            return "io.github.thefishlive.upload.gist.GistData.GistFile(content=" + this.getContent() + ")";
        }
    }
}
