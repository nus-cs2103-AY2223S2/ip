package duke.exception;

/**
 * Handles error in Duke Application.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor of DukeException class.
     *
     * @param message exception message when thrown.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return the exception message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
