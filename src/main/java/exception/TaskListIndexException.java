package exception;

/**
 * Thrown when a <code>TaskList</code> has been accessed with an illegal index.
 */
public class TaskListIndexException extends ArrayIndexOutOfBoundsException {
    /**
     * Constructs a <code>TaskListIndexException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public TaskListIndexException(String message) {
        super(message);
    }
}
