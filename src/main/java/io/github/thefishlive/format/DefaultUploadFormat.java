package io.github.thefishlive.format;

public class DefaultUploadFormat implements Format {
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
        if (level > 5) level = 2;

        StringBuilder builder = new StringBuilder();
        for (int i = 5; i >= level; i--) {
            builder.append("=");
        }
        return builder.toString() + " ";
    }

    @Override
    public String code() {
        return "\n";
    }

    @Override
    public String list() {
        return "- ";
    }

    @Override
    public String bold() {
        return "";
    }

    @Override
    public String italic() {
        return "";
    }
}
