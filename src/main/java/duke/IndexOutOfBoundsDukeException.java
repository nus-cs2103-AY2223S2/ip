package duke;

/**
 * Exception for out of bound error.
 */
public class IndexOutOfBoundsDukeException extends DukeException {
    /**
     * Constructor.
     */
    public IndexOutOfBoundsDukeException() {
        super("Invalid index. Please try again. ");
    }
}
