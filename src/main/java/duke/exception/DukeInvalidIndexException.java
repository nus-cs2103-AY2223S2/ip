package duke.exception;

/**
 * DukeInvalidIndexException class.
 * Handles invalid index exception in Duke.
 */
public class DukeInvalidIndexException extends DukeException {

    /**
     * Constructor for DukeException.
     * @param message Message when program runs into an invalid index error.
     */
    public DukeInvalidIndexException(String message) {
        super(message);
    }

    /**
     * Returns error message when program runs into an invalid index error.
     * @return Error message.
     */
    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
