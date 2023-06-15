package angela.exception;

/**
 * Indicates that a command is invalid.
 */
public class InvalidCommandAngelaException extends AngelaException {
    private static final String ERROR_MESSAGE = "Invalid command!";

    /**
     * Creates an exception with the error message.
     */
    public InvalidCommandAngelaException() {
        super(ERROR_MESSAGE);
    }
}
