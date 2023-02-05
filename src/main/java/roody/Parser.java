package roody;
public class Parser {
    public Parser() {}
    public static String[] parse(String command) {
        // Read input
        String[] commands = command.toLowerCase().split("\\s", 0);
        for (String str : commands) {
            str.trim();
        }
        return commands;
    }
}
