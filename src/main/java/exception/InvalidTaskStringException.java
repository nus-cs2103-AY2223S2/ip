package exception;

/**
 * Represents an exception when the task string in the storage does not follow the Duke format.
 */
public class InvalidTaskStringException extends DukeException {

    public InvalidTaskStringException(String errorMessage) {
        super(errorMessage);
    }
}