package duke.exceptions;

/**
 * Represents an exception that occurred when task number provided is not a number.
 * @author pzhengze
 */
public class DukeInvalidTaskNumberException extends DukeException {
    /**
     * Constructor for DukeInvalidTakNumberException.
     */
    public DukeInvalidTaskNumberException() {
        super("OOPS!!! Task number must be a number.");
    }
}
