package duke.exception;

/**
 * Exception class for all Duke-related exceptions.
 *
 * @author Lian Kok Hai
 */
public class DukeException extends Exception {
    /**
     * Constructs new DukeException with given error message.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
