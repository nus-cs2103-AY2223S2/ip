package duke.utils;

/**
 * Contains utility methods for boolean.
 */
public abstract class BooleanUtils {
    /**
     * Returns true if a string is a boolean in string form. Otherwise, returns false.
     *
     * @param str The string to check.
     * @return True if a string is a boolean in string form. False otherwise.
     */
    public static boolean isBooleanStr(String str) {
        return str != null && (str.equals("true") || str.equals("false"));
    }
}
