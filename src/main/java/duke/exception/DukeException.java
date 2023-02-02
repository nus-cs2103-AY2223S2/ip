package duke.exception;

/**
 * Exception class for exceptions related to duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor of DukeException
     *
     * @param error The error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
