package parser;

import command.Command;
import command.Command.Commands;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoesNotExistCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ToDoCommand;
import command.UnMarkComamnd;

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
            return new UnMarkComamnd(input);
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
        case EXIT:
            return new ExitCommand();
        default:
            return new DoesNotExistCommand();
        }
    }
}
