package voile.common.exception;

/**
 * Exception that occurs when invalid values are assigned to arguments.
 */
public class VoileIllegalArgumentException extends VoileRuntimeException {

    /**
     * Constructs a default instance.
     */
    public VoileIllegalArgumentException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public VoileIllegalArgumentException(String message) {
        super(message);
    }
}
