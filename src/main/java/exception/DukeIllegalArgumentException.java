package exception;

public class DukeIllegalArgumentException extends Exception {
    public DukeIllegalArgumentException() {}

    public DukeIllegalArgumentException(String description) {
        super(description);
    }

    public DukeIllegalArgumentException(String description, Throwable cause) {
        super(description, cause);
    }

    public DukeIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
