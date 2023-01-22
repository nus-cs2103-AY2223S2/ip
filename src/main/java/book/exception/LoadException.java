package book.exception;

/**
 * Subclass of {@code BookException} thrown when an error occurs loading {@code TaskList} of
 * {@code Book} to {@code Storage}.
 */
public class LoadException extends BookException {
    /**
     * Initializes a {@code LoadException}.
     * @param message {@code String} message associated with the {@code LoadException}.
     */
    public LoadException(String message) {
        super(message);
    }
}
