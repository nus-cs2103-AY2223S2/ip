package duke;

import duke.commands.Command;

import duke.commands.TodoCommand;
import duke.commands.EventCommand;
import duke.commands.DeadlineCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.DeleteCommand;
import duke.commands.ListCommand;
import duke.commands.FarewellCommand;


public class Parser {
    public static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "Invalid Command received.";
    public Command parse(String input) throws DukeException {
        String tokens[] = input.split("\\s");
        String commandWord = tokens[0];

        Command command;
        switch (commandWord) {
        case TodoCommand.COMMAND_FORMAT:
            command = new TodoCommand(tokens);
            break;
        case DeadlineCommand.COMMAND_FORMAT:
            command = new DeadlineCommand(tokens);
            break;
        case EventCommand.COMMAND_FORMAT:
            command = new EventCommand(tokens);
            break;
        case MarkCommand.COMMAND_FORMAT:
            command = new MarkCommand(tokens);
            break;
        case UnmarkCommand.COMMAND_FORMAT:
            command = new UnmarkCommand(tokens);
            break;
        case DeleteCommand.COMMAND_FORMAT:
            command = new DeleteCommand(tokens);
            break;
        case ListCommand.COMMAND_FORMAT:
            command = new ListCommand(tokens);
            break;
        case FarewellCommand.COMMAND_FORMAT:
            command = new FarewellCommand(tokens);
            break;
        default:
            throw new DukeException(INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
        return command;
    }
}
