package duke.exceptions;

/**
 * Represents an exception that occurs when description is missing while creating a Task.
 * @author pzhengze
 */
public class DukeMissingDescriptionException extends DukeException {
    /**
     * Constructor for DukeMissingDescriptionException.
     */
    public DukeMissingDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }
}
