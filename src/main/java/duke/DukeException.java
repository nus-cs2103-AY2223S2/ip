package duke;

/**
 * Exception thrown from Duke's code.
 */
public class DukeException extends Exception {
    /**
     * Returns a DukeException with errorMessage stored
     *
     * @param errorMessage String of the error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}