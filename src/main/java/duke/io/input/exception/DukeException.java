package duke.io.input.exception;

/**
 * An implementation of the {@code Exception} class, and a template
 * for Duke to handle errors and exceptions
 *
 */

public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
