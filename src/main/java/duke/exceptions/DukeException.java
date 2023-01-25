package duke.exceptions;

public class DukeException extends Exception {
    /**
     * Raises a duke exception.
     *
     * @param error Error message to be displayed.
     */
    public DukeException(String error) {
        super(error);
    }
}
