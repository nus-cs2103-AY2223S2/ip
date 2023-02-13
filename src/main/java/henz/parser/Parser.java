package henz.parser;

import henz.command.Command;
import henz.command.Command.Commands;
import henz.command.DeadlineCommand;
import henz.command.DeleteCommand;
import henz.command.DoesNotExistCommand;
import henz.command.EditDescriptionCommand;
import henz.command.EventCommand;
import henz.command.ExitCommand;
import henz.command.FindCommand;
import henz.command.ListCommand;
import henz.command.MarkCommand;
import henz.command.ToDoCommand;
import henz.command.UnmarkComamnd;

/**
 * An abstract class to make sense of the user command.
 */
public abstract class Parser {

    /**
     * Parses the commands of the user.
     * @param input the commands from the user
     * @return the Command
     */
    public static Command parse(String input) {
        Commands cmd = Command.getCommand(input);

        switch (cmd) {
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(input);
        case UNMARK:
            return new UnmarkComamnd(input);
        case MARK:
            return new MarkCommand(input);
        case TODO:
            return new ToDoCommand(input);
        case DEADLINE:
            return new DeadlineCommand(input);
        case EVENT:
            return new EventCommand(input);
        case DELETE:
            return new DeleteCommand(input);
        case EDIT:
            return new EditDescriptionCommand(input);
        case EXIT:
            return new ExitCommand();
        default:
            return new DoesNotExistCommand();
        }
    }
}
