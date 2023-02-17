package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.SearchCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

/**
 * Class that parses user inputs into commands for Duke
 */
public class Parser {
    public static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "Invalid Command received.";

    public static final Command EXIT_COMMAND = null;
    static final String EXIT_COMMAND_WORD = "bye";
    private String getCommandWord(String input) {
        int firstWhitespaceIndex = input.indexOf(" ");
        if (firstWhitespaceIndex == -1) {
            return input;
        } else {
            return input.substring(0, firstWhitespaceIndex);
        }
    }

    /**
     * Parses user input into a Command
     *
     * @param input User input
     * @return Support Duke command
     * @throws DukeException If command keyword in input is invalid
     */
    public Command parse(String input) throws DukeException {
        String commandWord = getCommandWord(input);

        Command command;
        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            command = new TodoCommand(input);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = new DeadlineCommand(input);
            break;
        case EventCommand.COMMAND_WORD:
            command = new EventCommand(input);
            break;
        case MarkCommand.COMMAND_WORD:
            command = new MarkCommand(input);
            break;
        case UnmarkCommand.COMMAND_WORD:
            command = new UnmarkCommand(input);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(input);
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand(input);
            break;
        case SearchCommand.COMMAND_WORD:
            command = new SearchCommand(input);
            break;
        case EXIT_COMMAND_WORD:
            command = EXIT_COMMAND;
            break;
        default:
            throw new DukeException(INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
        return command;
    }
}
