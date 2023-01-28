package sebastian.exceptions;

/**
 * Exception when the specific task the user wish to operate on does not exist
 */
public class TaskNotExistException extends SebastianException {
    /**
     * Constructor
     */
    public TaskNotExistException() {
        super("Sorry, the task does not exist");
    }
}
