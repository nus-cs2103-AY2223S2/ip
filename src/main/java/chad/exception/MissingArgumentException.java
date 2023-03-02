package chad.exception;

/**
 * Exception for when user missed out arguments that are supposed to be in the command.
 */
public class MissingArgumentException extends DukeException {

    /**
     * Constructor to create an exception for Missing argument.
     * @param message error message for the missing argument duke.exception.
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}
