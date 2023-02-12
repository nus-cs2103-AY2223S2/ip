package duke.util;

/**
 * Represents an exception that is thrown by Duke.
 * The messages are passed to the DukeException object.
 * The DukeException object is then thrown.
 * The message is then printed by the Ui object.
 */
public class DukeException extends Exception {

    /**
     * Represents a constructor for a new DukeException object to
     * provide the message to show to user.
     *
     * @param message is a string to be printed by the Ui object.
     */
    public DukeException(String message) {
        super(message);
    }
}
