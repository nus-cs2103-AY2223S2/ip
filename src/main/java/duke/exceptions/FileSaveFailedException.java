package duke.exceptions;

/**
 * Exception to be thrown when there are errors when writing to a file.
 */
public class FileSaveFailedException extends DukeException {
    /**
     * Constructor for an FileSaveFailedException.
     */
    public FileSaveFailedException() {
        super("Yabai! Could not save to file dayo...");
    }
}
