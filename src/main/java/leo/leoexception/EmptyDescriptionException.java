package leo.leoexception;

/**
 * Represents an exception when no description is given in a Task.
 */
public class EmptyDescriptionException extends LeoException {

    public EmptyDescriptionException(String message) {
        super(message);
    }
}
