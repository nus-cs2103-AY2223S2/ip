package duke;

public class EmptyDeadlineException extends DukeException {
    /**
     * Constructor
     */
    public EmptyDeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
}
