package duke.exception;

/**
 * A DukeException that deals with tasks without a task description.
 */
public class EmptyTaskDescriptionException extends DukeException {
    /**
     * Constructor for EmptyTaskDescriptionException.
     */
    public EmptyTaskDescriptionException() {
        super("The description of a task cannot be empty!");
    }
}
