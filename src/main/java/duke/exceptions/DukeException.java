package duke.exceptions;

/**
 * Parent Exception to all custom exceptions for the Duke project.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor for a DukeException with an optional message.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
