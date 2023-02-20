package voile.common.exception;

/**
 * Represents exceptions that are specific to the application.
 * <p>
 * Other exceptions of the application must inherit from this class.
 */
public class VoileRuntimeException extends RuntimeException {

    /**
     * Constructs a default instance.
     */
    public VoileRuntimeException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public VoileRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructs an instance with a specific error message and a root cause.
     *
     * @param message the error message
     * @param cause the root cause
     */
    public VoileRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an instance with a root cause.
     *
     * @param cause the root cause
     */
    public VoileRuntimeException(Throwable cause) {
        super(cause);
    }
}
