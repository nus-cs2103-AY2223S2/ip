package utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IndentHelper {
    public static String indent(int n, String s) {
        Stream<String> stream = s.lines();
        final String spaces = " ".repeat(n);
        stream = stream.map(sentence -> spaces + sentence);
        return stream.collect(Collectors.joining("\n"));
    }
}
