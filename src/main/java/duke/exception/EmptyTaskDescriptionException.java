package duke.exception;
public class EmptyTaskDescriptionException extends DukeException {
    /**
     * Constructor for EmptyTaskDescriptionException.
     */
    public EmptyTaskDescriptionException() {
        super("The description of a task cannot be empty!");
    }
}
