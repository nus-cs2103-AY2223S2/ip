package duke;

/**
 * An exception specific to the chatbot.
 *
 * @author wz2k
 */
public class DukeException extends Exception {
    /**
     * Creates a duke exception.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
