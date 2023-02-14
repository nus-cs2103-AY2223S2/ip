package iris.exception;

/**
 * encountered when no task is found on the given index
 */
public class UnknownTaskException extends IrisException {
    public UnknownTaskException() {
        super("Task not found. Please check the task index number again.");
    }
}
