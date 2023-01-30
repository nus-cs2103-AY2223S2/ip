package duke.exception;

/**
 * DukeException class.
 * Handles exceptions related to Duke objects.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param message Message when program runs into an error.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns error message when program runs into an error.
     * @return Error message.
     */
    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
