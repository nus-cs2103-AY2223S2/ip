package duke.exceptions;

/**
 * Represents an exception that occurs when there is a problem writing to a text file.
 * @author pzhengze
 */
public class DukeWriteException extends DukeException {
    /**
     * Constructor for DukeWriteException.
     */
    public DukeWriteException() {
        super("OOPS!!! Failed to write to save file:\n");
    }
}
