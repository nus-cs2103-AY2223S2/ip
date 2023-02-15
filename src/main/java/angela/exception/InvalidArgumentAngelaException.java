package angela.exception;

/**
 * Indicates that an argument of a command is invalid.
 */
public class InvalidArgumentAngelaException extends AngelaException {
    private static final String ERROR_MESSAGE = "Invalid argument!";

    /**
     * Creates an exception with the error message.
     */
    public InvalidArgumentAngelaException() {
        super(ERROR_MESSAGE);
    }
}
