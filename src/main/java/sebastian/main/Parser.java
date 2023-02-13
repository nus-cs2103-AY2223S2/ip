package sebastian.main;

import sebastian.command.AddDeadlineCommand;
import sebastian.command.AddEventCommand;
import sebastian.command.AddTodoCommand;
import sebastian.command.Command;
import sebastian.command.DeleteCommand;
import sebastian.command.ExitCommand;
import sebastian.command.FindCommand;
import sebastian.command.GetCommand;
import sebastian.command.ListCommand;
import sebastian.command.MarkCommand;
import sebastian.command.UnmarkCommand;
import sebastian.command.UpdateCommand;
import sebastian.exceptions.IllegalInputException;

/**
 * A class to interpret user input
 */
public class Parser {

    /**
     * To interpret user command and dispatch the job to different command handlers for execution
     * @param fullCommand user command
     * @return a command handler
     * @throws IllegalInputException when user command cannot be interpreted
     */
    public static Command parse(String fullCommand) throws IllegalInputException {
        String action = fullCommand.split(" ")[0];
        switch (action) {
        case "find":
            return new FindCommand(fullCommand);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(fullCommand);
        case "unmark":
            return new UnmarkCommand(fullCommand);
        case "todo":
            return new AddTodoCommand(fullCommand);
        case "deadline":
            return new AddDeadlineCommand(fullCommand);
        case "event":
            return new AddEventCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "get":
            return new GetCommand(fullCommand);
        case "update":
            return new UpdateCommand(fullCommand);
        case "bye":
            return new ExitCommand();
        default:
            throw new IllegalInputException();
        }
    }
}
