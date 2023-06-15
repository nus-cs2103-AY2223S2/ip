package angela.exception;

/**
 * Indicates that a save file cannot be read.
 */
public class CannotReadFileAngelaException extends AngelaException {
    private static final String ERROR_MESSAGE = "Cannot read file!";

    /**
     * Creates an exception with the error message.
     */
    public CannotReadFileAngelaException() {
        super(ERROR_MESSAGE);
    }
}
