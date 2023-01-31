package duke.exception;

/**
 * Represents an exception where a task is already marked.
 */
public class AlreadyMarkedException extends DukeException {
    public AlreadyMarkedException() {

    }

    public AlreadyMarkedException(String message) {
        super(message);
    }

    public AlreadyMarkedException(Throwable cause) {
        super(cause);
    }

    public AlreadyMarkedException(String message, Throwable cause) {
        super(message, cause);
    }
}
