package duke.exception;
public class EmptyTaskDescriptionException extends DukeException {
    public EmptyTaskDescriptionException() {
        super("The description of a task cannot be empty!");
    }
}
