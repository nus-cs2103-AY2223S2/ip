package duke;

/**
 * Represents exceptions that are specific to the application.
 * <p>
 * Other exceptions of the application must inherit from this class.
 */
public class DukeException extends RuntimeException {

    public DukeException() {}

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }
}
