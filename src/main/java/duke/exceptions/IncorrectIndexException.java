package duke.exceptions;

/**
 * Exception is thrown when index is <0 or more than size of task list
 */
public class IncorrectIndexException extends NeroException {

    public IncorrectIndexException(int taskListSize) {
        super(String.format("Please add the correct index from 1 to %d", taskListSize));
    }
}
