package duke.command;

import duke.exception.DukeException;

/**
 * Deals with making sense of the user input.
 */
public class Parser {
    private enum CommandName {
        BYE, B, LIST, L, MARK, M, UNMARK, U, DELETE, D, TODO, T, DEADLINE, DL, EVENT, E, FIND, F
    }

    /**
     * Deals with making sense of the user input.
     * Returns a command object.
     * Throws exception if user input is not a recognizable command.
     *
     * @param input Contains the command name or other information to create command object.
     * @return The appropriate command object that can be executed.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        CommandName cn = CommandName.valueOf(inputArray[0].toUpperCase());

        switch(cn) {
        case BYE:
        case B:
            return new ByeCommand(input);

        case LIST:
        case L:
            return new ListCommand(input);

        case MARK:
        case M:
            return new MarkCommand(input);

        case UNMARK:
        case U:
            return new UnmarkCommand(input);

        case DELETE:
        case D:
            return new DeleteCommand(input);

        case TODO:
        case T:
        case TD:
            return new TodoCommand(input);

        case FIND:
        case F:
            return new FindCommand(input);

        case DEADLINE:
        case DL:
            return new DeadlineCommand(input);

        case EVENT:
        case E:
            return new EventCommand(input);

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
    }
}
