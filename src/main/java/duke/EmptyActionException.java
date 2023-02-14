package duke;

public class EmptyActionException extends DukeException {

    /**
     * Constructor.
     */
    public EmptyActionException() {
        super("OOPS!!! The description of an action cannot be empty.");
    }
}
