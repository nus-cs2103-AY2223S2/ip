package duke.utils;

/**
 * Contains utility methods for boolean.
 */
public abstract class BooleanUtils {
    /**
     * Returns true if a string is a boolean in string form, otherwise, false.
     *
     * @param string The string to check.
     * @return True if a string is a boolean in string form, otherwise false.
     */
    public static boolean isBooleanString(String string) {
        return string.equals("true") || string.equals("false");
    }
}
