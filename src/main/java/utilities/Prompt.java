package utilities;

/**
 * A helper class to hold prompt string.
 * TODO: use a resource file to store strings.
 */
public final class Prompt {
    public static String beforeInput() {
        return "\uE0B4 ";
    }

    public static String afterInput() {
        return "\n";
    }
}
