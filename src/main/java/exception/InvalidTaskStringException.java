package exception;

/**
 * Represents an exception when the task string in the storage does not follow the Duke format.
 */
public class InvalidTaskStringException extends DukeException {

    /**
     * Constructor for InvalidTaskStringException
     * @param errorMessage Message for the error.
     */
    public InvalidTaskStringException(String errorMessage) {
        super(errorMessage);
    }
}