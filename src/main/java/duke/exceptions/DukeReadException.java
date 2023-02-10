package duke.exceptions;

/**
 * Represents an exception while reading a text file.
 * @author pzhengze
 */
public class DukeReadException extends DukeException {
    /**
     * Constructor for DukeReadException.
     */
    public DukeReadException() {
        super("OOPS!!! Failed to read from save file.");
    }
}
