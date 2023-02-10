package duke.parser;

import duke.command.*;

import java.util.Arrays;
public class Parser {
    private final static String[] VALID_COMMANDS = {"mark", "unmark", "list", "delete", "bye", "todo", "event", "deadline"};

    /**
     * Returns a boolean stating if the command is valid.
     *
     * @param input Command to check.
     * @return True if command is valid, False otherwise.
     */
    public boolean isValidCommand(String input) {
        return Arrays.asList(VALID_COMMANDS).contains(input.split(" ")[0]);
    }

    public boolean checkFind(String input) {
        return input.split(" ")[0].equals("find");
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

    public Command parse(String input) {
        Command cmd;
        if (checkList(input)) {
            cmd = new ListCommand();
        } else if (checkFind(input)) {
            String word = input.split(" ")[1];
            cmd = new FindCommand(word);
        } else if (checkMark(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new MarkCommand(num);
        } else if (checkUnmark(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new UnmarkCommand(num);
        } else if (checkDelete(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new DeleteCommand(num);
        } else if (checkTask(input)) {
            cmd = new AddCommand(input);
        } else if (!isValidCommand(input)) {
            cmd = new InvalidCommand();
        } else {
            cmd = new ByeCommand();
        }
        return cmd;
    }
}