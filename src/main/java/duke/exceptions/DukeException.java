package duke.exceptions;

/**
 * Custom exception class for Duke application.
 *
 * @author owen-yap
 *
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errMsg The error message to be displayed to the user.
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
