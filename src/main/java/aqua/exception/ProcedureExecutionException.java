package aqua.exception;

public class ProcedureExecutionException extends Exception {
    public ProcedureExecutionException() {}

    public ProcedureExecutionException(String msg) {
        super(msg);
    }

    public ProcedureExecutionException(Throwable cause) {
        super(cause);
    }

    public ProcedureExecutionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
