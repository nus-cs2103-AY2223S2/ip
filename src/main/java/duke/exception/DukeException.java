package duke.exception;

/**
 * An exception thrown when the bot encounters an issue during query processing.
 */
public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
