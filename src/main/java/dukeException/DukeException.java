package dukeException;

/**
 * Runtime exception specially made to handle exception Duke may encounter.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor to create Duke Exception
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
