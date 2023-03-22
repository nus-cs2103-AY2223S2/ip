package cluck.exceptions;

/**
 * Task not found exception is thrown when the task is not found in the task list.
 */
public class TaskNotFoundException extends CluckException {
    /**
     * Instantiates a new Task not found exception.
     *
     * @param errorMessage The error message.
     */
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
