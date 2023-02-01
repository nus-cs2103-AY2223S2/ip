package dudu.exception;

/**
 * Exception class on task index out of range.
 */
public class TaskNumRangeException extends DuduException {
    /**
     * Constructor for task index out of range exception.
     * @param msg Error message.
     */
    public TaskNumRangeException(String msg) {
        super(msg);
    }
    @Override
    public String toString() {
        return "ERROR!!! The task number is not in the range.";
    }
}
