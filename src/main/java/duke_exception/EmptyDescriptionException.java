package duke_exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of your task cannot be empty.");
    }
}
