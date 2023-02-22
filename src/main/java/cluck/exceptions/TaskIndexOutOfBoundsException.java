package cluck.exceptions;

import cluck.messages.Messages;
/**
 * Task index out of bounds exception is thrown when the user gives an index that is outside task list's range.
 */
public class TaskIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Instantiates a new Task index out of bounds exception.
     */
    public TaskIndexOutOfBoundsException() {
        super(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
    }
}
