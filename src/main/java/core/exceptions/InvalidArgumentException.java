package core.exceptions;

/**
 * The exception thrown when the argument does not match the expected.
 */
public class InvalidArgumentException extends Exception {
    /**
     * The tokens that caused this exception to be thrown, may be null.
     */
    final String[] tokens;

    /**
     * Creates a new exception due to not having a valid argument.
     *
     * @param message the message, if any.
     * @param tokens  the tokens because of which this exception is thrown.
     */
    public InvalidArgumentException(String message, String[] tokens) {
        super(message);
        this.tokens = tokens;
    }

    /**
     * Creates a new exception due to not having a valid argument.
     *
     * @param message the message of the argument.
     */
    public InvalidArgumentException(String message) {
        this(message, null);
    }

    @Override
    public String toString() {
        if (tokens == null) {
            return "InvalidArgumentException: " + super.getMessage();
        } else {
            return "InvalidArgumentException: " + super.getMessage() +
                    "[" + String.join(", ", tokens) + "]";
        }
    }
}
