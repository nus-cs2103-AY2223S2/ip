package duke;

/**
 * Exception thrown for missing descriptions for tasks
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Constructor for new MissingDescriptionException instance
     */
    public MissingDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.\n");
    }

    /**
     * String representation of MissingDescriptionException
     * 
     * @return string for the MissingDescriptionException
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.\n";
    }
}
