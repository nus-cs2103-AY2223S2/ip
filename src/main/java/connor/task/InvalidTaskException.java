package connor.task;

/**
 * Exception to be thrown for invalid inputs.
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task description");
    }
}
