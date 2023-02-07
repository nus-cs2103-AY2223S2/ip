package duke;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.FindDateCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;


/**
 * The class to generate Command objects.
 */
public class Parser {

    private enum Type {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, FINDDATE
    }

    /**
     * Method that creates appropriate objects based on the input string.
     *
     * @param input The user's input.
     * @throws IllegalArgumentException  If user's input does not correspond to any of the cases.
     */
    public static Command parse(String input) throws IllegalArgumentException {
        String[] words = input.split(" ");
        Type t = Type.valueOf(words[0].toUpperCase());
        switch(t) {
        case LIST:
            return new ListCommand(input);

        case DEADLINE:
            return new DeadlineCommand(input);

        case UNMARK:
            return new UnmarkCommand(input);

        case TODO:
            return new TodoCommand(input);

        case EVENT:
            return new EventCommand(input);

        case DELETE:
            return new DeleteCommand(input);

        case MARK:
            return new MarkCommand(input);

        case FIND:
            return new FindCommand(input);

        case FINDDATE:
            return new FindDateCommand(input);

        case BYE:
            return new ByeCommand(input);

        default:
            throw new IllegalArgumentException();
        }
    }
}
