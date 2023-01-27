package duke.exception;

/**
 * DukeException is the base class for custom exceptions related to Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
