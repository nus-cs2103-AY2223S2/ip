package book.exception;

/**
 * Subclass of {@code BookException} thrown when input to {@code Book} is invalid.
 */
public class InvalidInputException extends BookException {
    /**
     * Initializes an {@code InvalidInputException}.
     * @param message {@code String} message associated with the {@code InvalidInputException}.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
