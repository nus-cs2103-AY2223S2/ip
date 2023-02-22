package duke.exception;

/**
 * Creates the DukeException class
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException.
     *
     * @param message Duke error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
