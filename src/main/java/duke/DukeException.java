package duke;

/**
 * Consists of custom run-time exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
