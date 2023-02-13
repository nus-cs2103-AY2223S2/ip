package chad.exception;

/**
 * Runtime duke.exception specially made to handle duke.exception duke.Duke may encounter.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor to create duke.Duke Exception
     * @param message error message of the exception
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "YIKES!! " + super.getMessage();
    }
}
