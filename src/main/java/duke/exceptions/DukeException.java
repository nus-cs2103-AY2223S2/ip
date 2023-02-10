package duke.exceptions;

/**
 * Represents a Duke exception.
 * @author Samarth Verma
 */
public class DukeException extends Exception {
    /**
     * Creates a Duke exception.
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Creates a Duke exception.
     * @param message The error message.
     * @param cause The cause of the exception.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
