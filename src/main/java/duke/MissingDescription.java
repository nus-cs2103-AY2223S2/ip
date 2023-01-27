package duke;

public class MissingDescription extends DukeException {
    public MissingDescription() {
        super("OOPS!!! The description of a task cannot be empty");
    }
}
