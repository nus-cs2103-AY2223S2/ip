package parser;

import commands.*;

/**
 * Provide parsing mechanism for the user inputs
 */
public class Parser {
    public static Command parse(String command) {
        String[] splittedCmd = command.split(" ", 2);
        switch (splittedCmd[0]) {
            case "bye":
                return new ExitCommand(splittedCmd[0]);
            case "list":
                return new ListCommand(splittedCmd[0]);
            case "mark":
                return new MarkCommand(splittedCmd[1]);
            case "unmark":
                return new UnmarkCommand(splittedCmd[1]);
            case "delete":
                return new DeleteCommand(splittedCmd[1]);
            case "todo":
                return new ToDoCommand(splittedCmd[1]);
            case "deadline":
                return new DeadlineCommand(splittedCmd[1]);
            case "event":
                return new EventCommand(splittedCmd[1]);
            case "find":
                return new FindCommand(splittedCmd[1]);
            default:
                return new InvalidCommand(splittedCmd[0]);
        }
    }
}
