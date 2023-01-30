package duke.exception;

/**
 * DukeInvalidArgumentException class.
 * Handles invalid argument exceptions encountered by Duke.
 */
public class DukeInvalidArgumentException extends DukeException {

    /**
     * Constructor for DukeException.
     * @param message Message when program runs into an invalid argument error.
     */
    public DukeInvalidArgumentException(String message) {
        super(message);
    }

    /**
     * Returns error message when program runs into an invalid argument error.
     * @return Error message.
     */
    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
