/**
 * Encapsulation of an incomplete description exception.
 */
public class IncompleteDescException extends DukeException {
    String taskType;

    /**
     * Constructor for IncompleteDescException
     * @param message The error message.
     */
    public IncompleteDescException(String message) {
        super(message);
    }
}
