package duke.command;

import duke.exception.DukeException;

/**
 * This class interprets command.
 */
public class Parser {

    /**
     * Parses the input string as a command.
     *
     * @param s
     * @throws DukeException
     */
    public static Command parseCommand(String s) throws DukeException {
        CommandWords command = readCommandWord(s);
        switch (command) {
        case LIST:
            return new ListCommand();
        case PRIORITY:
            return new PriorityCommand();
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
        case BYE:
            return new ExitCommand();
        default:
            return new UnknownCommand();
        }
    }

    private static CommandWords readCommandWord(String s) throws DukeException {
        try {
            return CommandWords.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandWords.UNKNOWN;
        }
    }
}
