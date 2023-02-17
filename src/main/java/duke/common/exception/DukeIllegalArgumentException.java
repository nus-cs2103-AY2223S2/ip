package duke.common.exception;

/**
 * Exception that occurs when invalid values are assigned to arguments.
 */
public class DukeIllegalArgumentException extends DukeRuntimeException {

    /**
     * Constructs a default instance.
     */
    public DukeIllegalArgumentException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}
