package io.github.thefishlive.format;

public interface Format {

    public String newLine();

    public String paragraphBreak();

    public String header(int level);

    public String code();

    public String list();

    public String bold();

    public String italic();

}
