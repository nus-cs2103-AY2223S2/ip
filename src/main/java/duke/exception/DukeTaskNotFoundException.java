package duke.exception;

/**
 * DukeTaskNotFoundException class.
 * Handles when task is not found in Duke.
 */
public class DukeTaskNotFoundException extends DukeException {
    public DukeTaskNotFoundException(String message) {
        super(message);
    }

    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
