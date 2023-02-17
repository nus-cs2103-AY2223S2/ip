package duke;

/**
 * Exception for out of bound error.
 */
public class IndexOutOfBoundsDukeException extends DukeException {
    private static final String ERROR_MESSAGE = "Invalid index.";

    /**
     * Constructor.
     */
    public IndexOutOfBoundsDukeException() {
        super(wrapWithEncouragingWords(ERROR_MESSAGE));
    }
}
