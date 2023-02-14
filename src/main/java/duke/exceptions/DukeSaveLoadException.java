package duke.exceptions;

/**
 * The exception that's thrown when there's a problem with saving/loading Duke's
 * saved data.
 */
public class DukeSaveLoadException extends DukeException {
    public DukeSaveLoadException(String message) {
        super(message);
    }
}
