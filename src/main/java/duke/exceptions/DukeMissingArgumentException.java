package duke.exceptions;

/**
 * Represents a Missing Argument exception.
 *
 * @author Samarth Verma
 */
public class DukeMissingArgumentException extends DukeException {

    /**
     * Creates a Missing Argument exception.
     *
     * @param message The error message.
     */
    public DukeMissingArgumentException(String message) {
        super(message);
    }

    /**
     * Creates a Missing Argument exception.
     *
     * @param message The error message.
     * @param cause   The cause of the exception.
     */
    public DukeMissingArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
