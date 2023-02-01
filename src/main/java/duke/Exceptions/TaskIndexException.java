package duke.Exceptions;

public class TaskIndexException extends DukeException {
    private int max;
    public TaskIndexException() {
    }

    public TaskIndexException(int max) {
        this.max = max;
    }

    public TaskIndexException(String message) {
        super(message);
    }

    public TaskIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskIndexException(Throwable cause) {
        super(cause);
    }

    public TaskIndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "Sorry! Index is out of bounds...\nMaximum value: " + this.max;
    }
}
