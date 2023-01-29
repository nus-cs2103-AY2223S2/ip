package exception;

/**
 * Thrown when an argument could not be parsed as a valid <code>Task</code>.
 */
public class TaskParseException extends Exception {
    /**
     * Constructs a <code>TaskParseException</code> with the specified detail message.
     *
     * @param message the detail message
     */
    public TaskParseException(String message) {
        super(message);
    }
}
