package duke;

/**
 * Custom exception to throw if there is a bug within the application.
 */
public class DukeException extends Exception {
    /**
     * Main constructor (for invocation by other classes to throw the exception.
     *
     * @param msg The error message.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
