import java.util.Scanner;

public class Parser {
    public static String getFirstWord(String command) {
        return command.split(" ", 2)[0];
    }

    public static int getIndex(String command) {
        return Integer.parseInt( command.split(" ", 2)[1]) - 1;
    }

    public static String getDescription(String command) {
        return command.split(" ", 2)[1];
    }

    public static String[]  parseDeadline (String command) {
        return command.split(" ", 2)[1].split(" /by ");
    }

    public static String[] parseEvent (String command) {
        return command.split(" ", 2)[1].split("/from | /to ");
    }

}
