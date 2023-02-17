package duke.common.exception;

/**
 * Exception that occurs when an operation is trying to modify the application into an illegal
 * state.
 */
public class DukeIllegalStateException extends DukeRuntimeException {

    /**
     * Constructs a default instance.
     */
    public DukeIllegalStateException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public DukeIllegalStateException(String message) {
        super(message);
    }
}
