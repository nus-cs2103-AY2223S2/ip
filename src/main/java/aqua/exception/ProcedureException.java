package aqua.exception;

/** Signals that an error has occured while executing a set procedure. */
public class ProcedureException extends Exception {
    /**
     * Constructs a {@code ProcedureException} without any message or cause.
     */
    public ProcedureException() {}

    /**
     * Constructs a {@code ProcedureException} with the specified message.
     *
     * @param msg - the message of the exception.
     */
    public ProcedureException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code ProcedureException} with the specified cause.
     *
     * @param cause - the cause of the exception.
     */
    public ProcedureException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code ProcedureException} with the specified message and
     * cause.
     *
     * @param msg - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public ProcedureException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
