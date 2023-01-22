package book.exception;

/**
 * {@code Exception} class from which {@code BookException}s thrown during the execution of
 * {@code Book} extend from.
 */
public class BookException extends Exception {
    /**
     * Initializes a {@code BookException}.
     * @param message {@code String} message associated with the {@code BookException}.
     */
    public BookException(String message) {
        super(message);
    }
}
