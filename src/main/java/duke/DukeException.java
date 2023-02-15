package duke;

/**
 * General exception for the Duke command for displaying messages to user
 */
public class DukeException extends Exception {
    private static final String CUSTOM_PREFIX = "Something's not right.\n";

    /**
     * Constructor for DukeException that adds a prefix to the given message
     * @param message Main body of exception message
     */
    public DukeException(String message) {
        super(CUSTOM_PREFIX + message);
    }
}
