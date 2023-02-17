package duke;

/**
 * Encapsulates the exceptions within chat bot.
 */
public class DukeException extends Exception {

    /**
     * Error message to be shown to user.
     */
    public DukeException(String message) {
        super(message);
    };
}
