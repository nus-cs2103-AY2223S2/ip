public class DescriptionException extends DukeException {
    public DescriptionException() {
    }

    public DescriptionException(String message) {
        super(message);
    }

    public DescriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DescriptionException(Throwable cause) {
        super(cause);
    }

    public DescriptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a task cannot be empty.";
    }
}
