package duke.utils;

/**
 * Contains utility methods for boolean.
 */
public abstract class BooleanUtils {
    /**
     * Returns true if a string is a boolean in string form, otherwise, returns false.
     *
     * @param str The string to check.
     * @return True if a string is a boolean in string form, otherwise, returns false.
     */
    public static boolean isBooleanStr(String str) {
        assert str != null;

        return str.equals("true") || str.equals("false");
    }
}
