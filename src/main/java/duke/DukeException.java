package duke;

/**
 * Exception for Duke methods to throw
 */
public class DukeException extends Exception {
    /**
     * Default exception thrown
     */
    public DukeException() {
        super();
    }

    /**
     * Exception with custom message
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Exception with custom message and cause
     *
     * @param message
     * @param cause
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with custom cause
     *
     * @param cause
     */
    public DukeException(Throwable cause) {
        super(cause);
    }
}
