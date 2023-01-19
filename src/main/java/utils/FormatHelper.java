package utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class containing formatting methods
 */
public final class FormatHelper {
    /**
     * Adds indentations of n spaces to each new line in the input string.
     * @param n Number of spaces to add to each new line.
     * @param s The String to add indentation to.
     * @return A String with n spaces of indentation added to each new line.
     */
    public static String indent(int n, String s) {
        Stream<String> stream = s.lines();
        final String spaces = " ".repeat(n);
        stream = stream.map(sentence -> spaces + sentence);
        return stream.collect(Collectors.joining("\n"));
    }
}
