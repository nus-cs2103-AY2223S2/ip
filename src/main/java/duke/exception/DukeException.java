package duke.exception;

/**
 * Indicates an exception that can be handled by Duke.
 */
public abstract class DukeException extends Exception {

    /**
     * Creates an exception with the given message.
     * @param message The message to display when the exception is printed.
     */
    public DukeException(String message) {
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
