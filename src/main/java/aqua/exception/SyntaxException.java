package aqua.exception;

/** Signals that the the a syntax is invalid. */
public class SyntaxException extends Exception {
    /** Constructs an {@code SyntaxException} without any message or cause. */
    public SyntaxException() {}

    /**
     * Constructs an {@code SyntaxException} with the specified message.
     *
     * @param description - the message of the exception.
     */
    public SyntaxException(String description) {
        super(description);
    }

    /**
     * Constructs an {@code SyntaxException} with the given message and cause.
     *
     * @param description - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public SyntaxException(String description, Throwable cause) {
        super(description, cause);
    }

    /**
     * Constructs an {@code SyntaxException} with the given cause.
     *
     * @param cause - the cause of the exception.
     */
    public SyntaxException(Throwable cause) {
        super(cause);
    }
}
