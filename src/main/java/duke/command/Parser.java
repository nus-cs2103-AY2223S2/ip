package duke.command;

import duke.exception.DukeException;

/**
 * This class interprets command.
 */
public class Parser {

    /**
     * Parses the input string to a specific command.
     *
     * @param s The string representation of a CommandWord.
     * @return The specific command to be executed.
     * @throws DukeException If the string cannot be parsed to a CommandWord.
     */
    public static Command parseCommand(String s) throws DukeException {
        CommandWords command = readCommandWord(s);
        switch (command) {
        case LIST:
            return new ListCommand();
        case PRIORITY:
            return new PriorityCommand();
        case FIND:
            return new FindCommand();
        case ECHO:
            return new EchoCommand();
        case MARK:
            return new MarkCommand();
        case UNMARK:
            return new UnmarkCommand();
        case TODO:
            return new AddToDoCommand();
        case DEADLINE:
            return new AddDeadlineCommand();
        case EVENT:
            return new AddEventCommand();
        case DELETE:
            return new DeleteCommand();
        case UNDO:
            return new UndoCommand();
        case BYE:
            return new ExitCommand();
        default:
            // default should be CommandWords.UNKNOWN
            return new UnknownCommand();
        }
    }

    private static CommandWords readCommandWord(String s) {
        try {
            return CommandWords.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandWords.UNKNOWN;
        }
    }
}
