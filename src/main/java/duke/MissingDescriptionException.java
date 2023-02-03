package duke;

public class MissingDescriptionException extends DukeException {

    public MissingDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.\n");
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.\n";
    }
}
