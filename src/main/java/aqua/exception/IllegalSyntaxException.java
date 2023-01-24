package aqua.exception;

/** Signals that the the given syntax is invalid. */
public class IllegalSyntaxException extends Exception {
    /** Constructs an IllegalSyntaxException without any message or cause. */
    public IllegalSyntaxException() {}

    /**
     * Constructs an IllegalSyntaxException with the specified message.
     * 
     * @param description - the message of the exception.
     */
    public IllegalSyntaxException(String description) {
        super(description);
    }

    /**
     * Constructs an IllegalSyntaxException with the given message and cause.
     * 
     * @param description - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public IllegalSyntaxException(String description, Throwable cause) {
        super(description, cause);
    }

    /**
     * Constructs an IllegalSyntaxException with the given cause.
     * 
     * @param cause - the cause of the exception.
     */
    public IllegalSyntaxException(Throwable cause) {
        super(cause);
    }
}
