package parser;

import java.util.Arrays;
import java.util.stream.Stream;
public class Parser {
    private static String[] VALID_COMMANDS = {"mark", "unmark", "list", "delete", "bye", "todo", "event", "deadline"};

    public boolean isValidCommand(String input) {
        return Arrays.stream(VALID_COMMANDS).anyMatch(input.split(" ")[0]::equals);
    }

    public boolean checkTodo(String input) {
        return input.split(" ")[0].equals("todo");
    }
    public boolean checkEvent(String input) {
        return input.split(" ")[0].equals("event");
    }
    public boolean checkDeadline(String input) {
        return input.split(" ")[0].equals("deadline");
    }

    public boolean checkTask(String input) {
        String cmd = input.split(" ")[0];
        return cmd.equals("deadline") || cmd.equals("todo") || cmd.equals("event");
    }
    public boolean checkMark(String input) {
        return input.split(" ")[0].equals("mark");
    }
    public boolean checkUnmark(String input) {
        return input.split(" ")[0].equals("unmark");
    }
    public boolean checkList(String input) {
        return input.split(" ")[0].equals("list");
    }
    public boolean checkBye(String input) {
        return input.split(" ")[0].equals("bye");
    }
    public boolean checkDelete(String input) {
        return input.split(" ")[0].equals("delete");
    }

}