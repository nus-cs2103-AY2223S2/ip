package duke.exceptions;

public class EmptyTaskListException extends NeroException {

    public EmptyTaskListException() {
        super("Task list is empty!!");
    }
}