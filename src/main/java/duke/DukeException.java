package duke;

/**
 * Encapsulates an exception to be thrown in the Duke app.
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException object.
     * @param s The message to be displayed in the exception.
     */
    DukeException(String s) {
        super(s);
    }
}
