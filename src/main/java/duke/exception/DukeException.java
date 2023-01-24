package duke.exception;

/**
 * Exception class.
 */
public class DukeException extends Exception {
    /**
     * Creates an instance of DukeException.
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
