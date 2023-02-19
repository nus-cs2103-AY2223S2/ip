package exceptions;

/**
 * Represents exceptions for requesting tasks not in the task list.
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructs an exception for requesting tasks not in the task list.
     */
    public TaskNotFoundException() {
        super("OOPS!!! The requested task was not found in the task list.");
    }
}
