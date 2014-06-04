package io.github.thefishlive.upload.gist;

import io.github.thefishlive.format.Format;

public class GistUploadFormat implements Format {
    @Override
    public String newLine() {
        return "\n";
    }

    @Override
    public String paragraphBreak() {
        return "\n\n";
    }

    @Override
    public String header(int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append("#");
        }
        return builder.toString();
    }

    @Override
    public String code() {
        return "\n```\n";
    }

    @Override
    public String list() {
        return "- ";
    }

    @Override
    public String bold() {
        return "__";
    }

    @Override
    public String italic() {
        return "*";
    }
}
