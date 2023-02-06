package duke;

/**
 * Class to represent exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object with the default message.
     */
    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Constucts a DukeException object with the given message.
     * @param message The given message.
     */
    public DukeException(String message) {
        super(message);
    }
}
