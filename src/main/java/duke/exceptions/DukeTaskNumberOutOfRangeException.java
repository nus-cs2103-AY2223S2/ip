package duke.exceptions;

/**
 * Represents an exception that occurred when task number provided is out of range.
 * @author pzhengze
 */
public class DukeTaskNumberOutOfRangeException extends DukeException {
    /**
     * Constructor for DukeTaskNumberOutOfRangeException.
     */
    public DukeTaskNumberOutOfRangeException() {
        super("OOPS!!! Task number out of range.");
    }
}
