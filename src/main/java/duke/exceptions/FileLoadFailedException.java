package duke.exceptions;

/**
 * Exception to be thrown when there are errors when loading from a file.
 */
public class FileLoadFailedException extends DukeException {
    /**
     * Constructor for a FileLoadFailedException.
     */
    public FileLoadFailedException() {
        super("Yabai! Could not load from file dayo... dousyou...");
    }
}
