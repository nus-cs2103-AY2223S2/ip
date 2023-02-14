package duke.exceptions;

/**
 * The exception that's thrown when a command is given invalid arguments.
 * (eg. "mark NOT_AN_INDEX", "deadline EMPTY_BY_PARAM /by")
 */
public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String message) {
        super(message);
    }
}
