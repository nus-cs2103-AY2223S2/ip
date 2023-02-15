package angela.exception;

/**
 * Indicates an exception that can be handled by Angela.
 */
public abstract class AngelaException extends Exception {

    /**
     * Creates an exception with the given message.
     * @param message The message to display when the exception is printed.
     */
    public AngelaException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of the exception.
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "Sorry! " + super.getMessage();
    }
}
