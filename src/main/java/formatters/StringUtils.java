package formatters;

import java.util.Arrays;

/**
 * Utilities class to process strings.
 */
public class StringUtils {

    public StringUtils() {

    }

    /**
     * Returns a concatenated string coming from the start to end index of the array.
     * @param array string array
     * @param start start index
     * @param end end index
     * @return concatenated string of strings from start to end index of the array
     */
    public static String joinString(String[] array, int start, int end) {
        String string = "";
        for (int i = start; i < end; i++) {
            string += array[i] + " ";
        }
        return string + array[end];
    }

    /**
     * Removes whitespace or blanks from an array of strings.
     * @param array string array
     * @return new string array with no blanks or whitespaces
     */
    public static String[] removeWhiteSpace(String[] array) {
        return Arrays.stream(array).filter(x -> !x.isBlank()).toArray(String[]::new);
    }

    /**
     * Returns an index of the array for which a specific string is located, or -1 otherwise.
     * @param array string array
     * @param string string to be located
     * @return index of array in where the string is located, otherwise -1
     */
    public static int searchString(String[] array, String string) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(string)) {
                return i;
            }
        }
        return -1;
    }
}
