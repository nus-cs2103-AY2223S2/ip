package util;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final List<Character> WS = List.of(' ', '\n', '\t', '\r');

    private Util() {
    }

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

    public static String listToString(List<Character> s) {
        return s.stream().map(c -> c.toString()).reduce("", (a, b) -> a + b);
    }
}
