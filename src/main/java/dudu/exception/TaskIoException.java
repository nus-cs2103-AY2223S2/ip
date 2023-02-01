package dudu.exception;

/**
 * Exception class on task input and output exception.
 */
public class TaskIoException extends DuduException {
    private String msg;

    /**
     * Constructor of the Task IO exception.
     * @param msg Error message.
     */
    public TaskIoException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
