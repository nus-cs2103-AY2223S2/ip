package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Static utility functions not tied to any class
 */
public class Util {

    private static final List<Character> WS = List.of(' ', '\n', '\t', '\r');

    private Util() {
    }

    /**
     * Splits string into list of strings by whitespace.
     *
     * @param str String to be split
     * @return List of strings
     */
    public static List<String> splitWhitespace(String str) {
        List<String> res = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (WS.contains(c) && !temp.isEmpty()) {
                res.add(temp);
                temp = "";
            } else if (!WS.contains(c)) {
                temp += c;
            }
        }
        if (!temp.isEmpty()) {
            res.add(temp);
        }
        return res;
    }

    /**
     * Clears whitespace at beginning and end of string
     *
     * @param str String to process
     * @return Resultant string
     */
    public static String cleanup(String str) {
        int start = 0;
        int end = str.length();
        while (start < str.length() && WS.contains(str.charAt(start))) {
            ++start;
        }
        while (end > 0 && WS.contains(str.charAt(end - 1))) {
            --end;
        }
        if (start > end) {
            return "";
        }

        return str.substring(start, end);
    }

    /**
     * Fuses list of characters into a single string
     *
     * @param s List of characters
     * @return Resultant string
     */
    public static String listToString(List<Character> s) {
        return s.stream().map(Object::toString).reduce("", (a, b) -> a + b);
    }

    /**
     * Joins strings using a specified delimiter
     *
     * @param delimiter Delimiter string
     * @param lines     Strings to join
     * @return Joined string
     */
    public static String join(String delimiter, String... lines) {
        return Stream.of(lines)
                .reduce("", (a, b) -> a + delimiter + b)
                .substring(delimiter.length());
    }

}
