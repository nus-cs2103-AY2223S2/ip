package duke;

/**
 * Consists of custom run-time exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a duke exception.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
