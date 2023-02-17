package duke.exception;

/**
 * Exception thrown when there is no description after the command
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
