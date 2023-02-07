package duke.Exceptions;

public class IncorrectIndexException extends NeroException {

    public IncorrectIndexException(int taskListSize) {
        super(String.format("Please add the correct index from 1 to %d", taskListSize));
    }
}
