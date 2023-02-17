package duke.logic;

import duke.commands.Command;
import duke.exceptions.DukeUnknownInputException;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.TodoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;

/**
 * Helps to parse user input into commands that the chat bot understands.
 * @author lukkesreysandeur
 */
public class Parser {
    /**
     * Parses the user inputted command and returns a command; throws error if command is invalid.
     *
     * @param fullCommand Command entered by the user.
     * @return Command object that will handle the requested action.
     * @throws DukeUnknownInputException
     */
    public Command parse(String fullCommand) throws DukeUnknownInputException {
        String sanitisedCommand = fullCommand.trim();
        String[] commands = sanitisedCommand.split(" ", 2);
        String command = commands[0];
        String params;
        if (commands.length != 2) {
            params = "";
        } else {
            params = commands[1];
        }
        switch (command.toLowerCase()) {
        case "list":
            return new ListCommand(params);
        case "mark":
            return new MarkCommand(params);
        case "unmark":
            return new UnmarkCommand(params);
        case "todo":
            return new TodoCommand(params);
        case "deadline":
            return new DeadlineCommand(params);
        case "event":
            return new EventCommand(params);
        case "delete":
            return new DeleteCommand(params);
        case "bye":
            return new ExitCommand(params);
        case "find":
            return new FindCommand(params);
        case "help":
            return new HelpCommand(params);
        default:
            throw new DukeUnknownInputException();
        }
    }
}
