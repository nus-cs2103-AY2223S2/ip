package duke.exception;

/**
 * Indicates that a command is invalid.
 */
public class InvalidCommandDukeException extends DukeException {
    public static final String ERROR_MESSAGE = "Invalid command!";

    /**
     * Creates an exception with the error message.
     */
    public InvalidCommandDukeException() {
        super(ERROR_MESSAGE);
    }
}
