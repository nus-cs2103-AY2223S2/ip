package iris.exception;

public class UnknownTaskException extends IrisException {
    public UnknownTaskException() {
        super("Task not found. Please check the task number again.");
    }
}
