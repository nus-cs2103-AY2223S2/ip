package duke;

public class EmptyDescriptionException extends DukeException{

    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.\n");
    }
}
