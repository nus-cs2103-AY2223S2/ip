package duke.exception;

/**
 * Indicates that an argument of a command is missing.
 */
public class EmptyArgumentDukeException extends DukeException {

    public static final String ERROR_MESSAGE = "One or more arguments are empty!";

    /**
     * Creates an exception with the error message.
     */
    public EmptyArgumentDukeException() {
        super(ERROR_MESSAGE);
    }
}
