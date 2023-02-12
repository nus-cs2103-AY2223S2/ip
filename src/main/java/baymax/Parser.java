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
import commands.InvalidCommand;
import commands.FindCommand;

public class Parser {
    
    private enum commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    private commands getCommand(String input) {
        return commands.valueOf(input.split(" ")[0].toUpperCase());
    }

    public Command parse(String input) {
        switch (getCommand(input)) {
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
        default:
            return new InvalidCommand(input);
        }
    }
}

