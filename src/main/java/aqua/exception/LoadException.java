package aqua.exception;

public class LoadException extends Exception {
    public LoadException() {}

    public LoadException(String msg) {
        super(msg);
    }

    public LoadException(Throwable cause) {
        super(cause);
    }

    public LoadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
