package duke.common.exception;

/**
 * Represents exceptions that are specific to the application.
 * <p>
 * Other exceptions of the application must inherit from this class.
 */
public class DukeRuntimeException extends RuntimeException {

    public DukeRuntimeException() {}

    public DukeRuntimeException(String message) {
        super(message);
    }

    public DukeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeRuntimeException(Throwable cause) {
        super(cause);
    }
}
