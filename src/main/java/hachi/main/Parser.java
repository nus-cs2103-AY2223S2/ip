package hachi.main;

import hachi.commands.*;

/**
 * Handles user instruction and perform the corresponding type of command.
 */
public class Parser {
    private enum Type {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    /**
     *
     * @param input The user's input string.
     * @return A specific type of command.
     * @throws IllegalArgumentException if command is unknown.
     */
    public static Command parse(String input) throws IllegalArgumentException {
        String[] words = input.split(" ");
        Type t = Type.valueOf(words[0].toUpperCase());
        switch (t) {
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

            case BYE:
                return new ExitCommand(input);

            case FIND:
                return new FindCommand(input);

            default:
                throw new IllegalArgumentException();
        }
    }
}

