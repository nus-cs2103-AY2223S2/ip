package duke;

/**
 * Exception for out of bound error.
 */
public class IndexOutOfBoundsDukeException extends DukeException {
    static String OUT_OF_BOUND_MESSAGE = "Invalid index. Please try again.";

    /**
     * Constructor.
     */
    public IndexOutOfBoundsDukeException() {
        super(OUT_OF_BOUND_MESSAGE);
    }
}
