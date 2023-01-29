package duke.exception;

/**
 * Exceptions that are specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Get the error message.
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }

}
