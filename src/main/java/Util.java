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

    public static Pair<String, String> parseNextString(String str) {
        int start = 0;
        while (start < str.length() && WS.contains(str.charAt(start))) {
            ++start;
        }
        int end = start;
        while (end < str.length() && !WS.contains(str.charAt(end))) {
            ++end;
        }
        return new Pair<>(str.substring(start, end), str.substring(end, str.length()));
    }
}
