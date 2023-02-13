package duke;

public class EmptyEventException extends DukeException {
    /**
     * Constructor
     */
    public EmptyEventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }
}
