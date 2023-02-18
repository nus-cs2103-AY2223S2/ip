package duke.exceptions;

/**
 * Thrown to indicate that an error related to the running of Duke has been encountered
 */

public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified error message.
     * @param errorMessage the error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
