package duke.Exceptions;

public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }

    public DukeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
