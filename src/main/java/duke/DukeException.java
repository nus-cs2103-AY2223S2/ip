package duke;

/**
 * DukeException represents an exception specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param errorMessage The error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
