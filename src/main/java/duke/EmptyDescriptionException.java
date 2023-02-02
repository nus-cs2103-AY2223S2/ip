package duke;
public class EmptyDescriptionException extends DukeException{

    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.\n");
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.\n";
    }
}
