package duke.exceptions;

/**
 * Represents an exception that occurred while creating a file.
 * @author pzhengze
 */
public class DukeFileCreationException extends DukeException {
    /**
     * Constructor for DukeFileCreationException.
     */
    public DukeFileCreationException() {
        super("OOPS!!! Save file cannot be created. Please restart Duke.");
    }
}
