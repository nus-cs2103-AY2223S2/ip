package uwuke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class to parse strings from save files 
 */
public abstract class StorageParser {
    public static String[] parseDeadline(String deadlineString) {
        Pattern p = Pattern.compile("\\[D\\]\\[[\\sX]\\]\\s(.+)\\s\\(by:\\s(.+)\\)");
        Matcher m = p.matcher(deadlineString);
        m.find();
        return new String[] {m.group(1), m.group(2)};
    }

    public static String[] parseEvent(String eventString) {
        Pattern p = Pattern.compile("\\[E\\]\\[[\\sX]\\]\\s(.+)\\s\\(from:\\s(.+)\\sto:\\s(.+)\\)");
        Matcher m = p.matcher(eventString);
        m.find();
        return new String[] {m.group(1), m.group(2), m.group(3)};
    }

    public static String parseTodo(String todoString) {
        Pattern p = Pattern.compile("\\[T\\]\\[[\\sX]\\]\\s(.+)");
        Matcher m = p.matcher(todoString);
        m.find();
        return m.group(1);
    }
}
