package duke.exception;

/**
 * Indicates that an argument of a command is invalid.
 */
public class InvalidArgumentDukeException extends DukeException {
    public static final String ERROR_MESSAGE = "Invalid argument!";

    /**
     * Creates an exception with the error message.
     */
    public InvalidArgumentDukeException() {
        super(ERROR_MESSAGE);
    }
}
