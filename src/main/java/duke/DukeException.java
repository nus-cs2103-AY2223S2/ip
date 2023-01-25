package duke;

/**
 * Represent exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message Error message.
     */
    protected DukeException(String message) {
        super(message);
    }
}
