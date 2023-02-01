package dudu.exception;

/**
 * Exception class for empty description in the command
 */
public class EmptyDescriptionException extends DuduException {
    private String type;
    private String error;

    /**
     * Constructor for empty description exception.
     * @param type Type of task.
     * @param error The error of the task.
     * @param msg Error message.
     */
    public EmptyDescriptionException(String type, String error, String msg) {
        super(msg);
        this.type = type;
        this.error = error;
    }
    @Override
    public String toString() {
        return "OOPS!!!\n The " + error + " of a " + type + " cannot be empty.";
    }
}
