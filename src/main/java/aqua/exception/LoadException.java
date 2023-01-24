package aqua.exception;

/** Signals that a loading error has occured. */
public class LoadException extends Exception {
    /** Constructs a LoadException without any message or cause. */
    public LoadException() {}

    /**
     * Constructs a LoadException with the specified message.
     * 
     * @param msg - the message of the exception.
     */
    public LoadException(String msg) {
        super(msg);
    }

    /**
     * Constructs a LoadException with the specified cause.
     * 
     * @param cause - the cause of the exception.
     */
    public LoadException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a LoadException with the specified message and cause.
     * 
     * @param msg - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public LoadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
