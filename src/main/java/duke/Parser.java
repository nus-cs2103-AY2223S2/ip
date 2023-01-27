package duke;

import duke.commands.Command;

import duke.commands.TodoCommand;
import duke.commands.EventCommand;
import duke.commands.DeadlineCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.DeleteCommand;
import duke.commands.ListCommand;

/**
 * Class that parses user inputs into commands for Duke
 */
public class Parser {
    public static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "Invalid Command received.";
    static final String EXIT_COMMAND = "bye";
    public Command parse(String input) throws DukeException {
        int firstWhitespaceIndex = input.indexOf(" ");
        String commandWord;
        if (firstWhitespaceIndex == -1) {
            commandWord = input;
        } else {
            commandWord = input.substring(0, firstWhitespaceIndex);
        }

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
        case UnmarkCommand.COMMAND_FORMAT:
            command = new UnmarkCommand(input);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(input);
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand(input);
            break;
            case EXIT_COMMAND:
            command = null;
            break;
        default:
            throw new DukeException(INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
        return command;
    }
}
