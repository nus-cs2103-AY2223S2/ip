package parser;


import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;
import commands.UpdateCommand;

/**
 * Provide parsing mechanism for the user inputs
 */
public class Parser {
    /**
     * Parse user input and returning a Command
     * @param command user input
     * @return Command object
     */
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
        case "update":
            return new UpdateCommand(splittedCmd[1]);
        default:
            return new InvalidCommand(splittedCmd[0]);
        }
    }
}
