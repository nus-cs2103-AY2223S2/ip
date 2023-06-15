package angela.exception;

/**
 * Indicates that the save file cannot be written to.
 */
public class CannotWriteFileAngelaException extends AngelaException {
    private static final String ERROR_MESSAGE = "Cannot write to file!";

    /**
     * Creates an exception with the error message.
     */
    public CannotWriteFileAngelaException() {
        super(ERROR_MESSAGE);
    }
}
