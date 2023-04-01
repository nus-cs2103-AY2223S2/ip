package baymax;

import commands.Command;
import commands.ByeCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import commands.ToDoCommand;
import commands.DeadlineCommand;
import commands.EventCommand;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.HelpCommand;

public class Parser {
    // enum for commands
    private enum commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, HELP
    }

    /**
     * Parses the input and returns the appropriate command.
     * @param input The input from the user.
     * @return The appropriate command.
     */
    public Command parse(String input) throws IllegalArgumentException {
        switch (commands.valueOf(input.split(" ")[0].toUpperCase())) {
        case BYE:
            return new ByeCommand(input);
        case LIST:
            return new ListCommand(input);
        case MARK:
            return new MarkCommand(input);
        case UNMARK:
            return new UnmarkCommand(input);
        case TODO:
            return new ToDoCommand(input);
        case DEADLINE:
            return new DeadlineCommand(input);
        case EVENT:
            return new EventCommand(input);
        case DELETE:
            return new DeleteCommand(input);
        case FIND:
            return new FindCommand(input);
        case HELP:
            return new HelpCommand(input);
        default:
            throw new IllegalArgumentException();
        }
    }
}

