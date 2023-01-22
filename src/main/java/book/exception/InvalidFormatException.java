package book.exception;

/**
 * Subclass of {@code BookException} thrown when format of input to {@code Book} is invalid.
 */
public class InvalidFormatException extends BookException {
    /**
     * Initializes an {@code InvalidFormatException}.
     * @param message {@code String} message associated with the {@code InvalidFormatException}.
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
