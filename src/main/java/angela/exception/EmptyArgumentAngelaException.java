package angela.exception;

/**
 * Indicates that an argument of a command is missing.
 */
public class EmptyArgumentAngelaException extends AngelaException {
    private static final String ERROR_MESSAGE = "One or more arguments are empty!";

    /**
     * Creates an exception with the error message.
     */
    public EmptyArgumentAngelaException() {
        super(ERROR_MESSAGE);
    }
}
