public class EmptyTaskDescriptionException extends DukeException {
    EmptyTaskDescriptionException() {
        super("The description of a task cannot be empty!");
    }
}
