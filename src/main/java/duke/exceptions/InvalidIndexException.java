package duke.exceptions;

/**
 * Exception that is similar in use to IndexOutOfBoundsException.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor for an InvalidIndexException.
     */
    public InvalidIndexException() {
        super("Not a valid index da yo~");
    }
}
