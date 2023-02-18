package kuromi.exception;

/**
 * Custom exception to throw if there is a bug within the application.
 */
public class KuromiException extends Exception {
    /**
     * Main constructor
     * (for invocation by other classes to throw the exception.
     *
     * @param msg The error message.
     */
    public KuromiException(String msg) {
        super(msg);
    }
}
