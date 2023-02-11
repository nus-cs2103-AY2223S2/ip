package duke.command;

import duke.exception.DukeException;

/**
 * Deals with making sense of the user input.
 */
public class Parser {
    private enum CommandName {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
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
            return new ByeCommand(input);

        case LIST:
            return new ListCommand(input);

        case MARK:
            return new MarkCommand(input);

        case UNMARK:
            return new UnmarkCommand(input);

        case DELETE:
            return new DeleteCommand(input);

        case TODO:
            return new TodoCommand(input);

        case FIND:
            return new FindCommand(input);

        case DEADLINE:
            return new DeadlineCommand(input);

        case EVENT:
            return new EventCommand(input);

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
    }
}
