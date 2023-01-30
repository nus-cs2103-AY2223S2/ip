package duke.exceptions;

/**
 * Represents an exception that is specific to Duke.
 * @author pzhengze
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param errorMsg The message to be printed to the user.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
