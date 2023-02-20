package voile.common.exception;

/**
 * Exception that occurs when trying to access memory with an invalid index.
 */
public class VoileIndexOutOfBoundsException extends VoileRuntimeException {

    /**
     * Constructs a default instance.
     */
    public VoileIndexOutOfBoundsException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public VoileIndexOutOfBoundsException(String message) {
        super(message);
    }
}
