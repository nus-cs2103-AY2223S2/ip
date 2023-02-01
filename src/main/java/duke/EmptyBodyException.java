package duke;

/**
 * Exception to be thrown when parts of a command are missing.
 */
public class EmptyBodyException extends DukeException {
    /**
     * Constructor for an EmptyBodyException with optional message.
     *
     * @param message Exception message
     */
    public EmptyBodyException(String message) {
        super(message);
    }

    /**
     * Constructor for an EmptyBodyException.
     */
    public EmptyBodyException() {
        super();
    }
}
