package duke.exception;

/**
 * Exception to handle errors specific to Duke.
 */
public class DukeException extends IllegalArgumentException {

    /**
     * Constructor for the DukeException class.
     * @param error the error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
