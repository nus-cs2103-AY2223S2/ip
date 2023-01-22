package book.exception;

/**
 * Subclass of {@code BookException} thrown when input to {@code Book} is incomplete.
 */
public class IncompleteInputException extends BookException {
    /**
     * Initializes an {@code IncompleteInputException}.
     * @param message {@code String} message associated with the {@code IncompleteInputException}.
     */
    public IncompleteInputException(String message) {
        super(message);
    }
}
