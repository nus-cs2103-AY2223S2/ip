package duke.exception;

public class DukeException extends Exception {
    /**
     * Constructor for Duke's own exception class.
     * @param message appropriate message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}