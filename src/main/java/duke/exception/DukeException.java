package duke.exception;

/**
 * Handles Exception by Duke
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor for DukeException
     * @param message
     * @param cause
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for DukeException
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructor for DukeException
     * @param cause
     */
    public DukeException(Throwable cause) {
        super(cause);
    }
}
