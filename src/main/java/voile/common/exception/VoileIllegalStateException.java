package voile.common.exception;

/**
 * Exception that occurs when an operation is trying to modify the application into an illegal
 * state.
 */
public class VoileIllegalStateException extends VoileRuntimeException {

    /**
     * Constructs a default instance.
     */
    public VoileIllegalStateException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public VoileIllegalStateException(String message) {
        super(message);
    }
}
