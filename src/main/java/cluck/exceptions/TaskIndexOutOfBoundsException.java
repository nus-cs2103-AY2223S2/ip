package cluck.exceptions;

/**
 * Task index out of bounds exception is thrown when the user gives an index that is outside task list's range.
 */
public class TaskIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Instantiates a new Task index out of bounds exception.
     */
    public TaskIndexOutOfBoundsException() {
        super("Index is out of range of Task list!");
    }
}
