package duke.exception;

/**
 * The DukeException class extends the Exception class and represents that an error has occured in Duke
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException.
     *
     * @param e The error message.
     */
    public DukeException(String e) {
        super("Duke: OOPS!!! " + e);
    }
}
