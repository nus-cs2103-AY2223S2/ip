package aqua.exception;

/** Signals that an error has occured while executing a set procedure. */
public class ProcedureExecutionException extends Exception {
    /** Constructs a ProcedureExecutionException without any message or cause. */
    public ProcedureExecutionException() {}

    /**
     * Constructs a ProcedureExecutionException with the specified message.
     * 
     * @param msg - the message of the exception.
     */
    public ProcedureExecutionException(String msg) {
        super(msg);
    }

    /**
     * Constructs a ProcedureExecutionException with the specified cause.
     * 
     * @param cause - the cause of the exception.
     */
    public ProcedureExecutionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a ProcedureExecutionException with the specified message and
     * cause.
     * 
     * @param msg - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public ProcedureExecutionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
