package duke;

/**
 * Exception that is similar in use to IndexOutOfBoundsException.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor for an InvalidIndexException with optional message.
     *
     * @param message Exception message.
     */
    public InvalidIndexException(String message) {
        super(message);
    }

    /**
     * Constructor for an InvalidIndexException.
     */
    public InvalidIndexException() {
        super();
    }
}
