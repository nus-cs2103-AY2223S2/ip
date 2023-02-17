package duke.common.exception;

/**
 * Exception that occurs when trying to access memory with an invalid index.
 */
public class DukeIndexOutOfBoundsException extends DukeRuntimeException {

    /**
     * Constructs a default instance.
     */
    public DukeIndexOutOfBoundsException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public DukeIndexOutOfBoundsException(String message) {
        super(message);
    }
}
