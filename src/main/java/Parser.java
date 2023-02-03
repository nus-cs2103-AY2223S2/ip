public class Parser {
    static String[] commands;
    public Parser(){}
    public static String[] Parse(String command) {
        // Read input
        commands = command.toLowerCase().split("\\s", 0);
        for (String str : commands) {
            str.trim();
        }
        return commands;
    }
}
