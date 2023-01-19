public class UnknownTaskException extends DukeException{
    public UnknownTaskException() {
        super("Task not found. Please check the task number again.");
    }
}
