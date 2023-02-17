package lele.exception;

/**
 * An exception when a valid user command does not
 * contain a description.
 */
public class EmptyDescException extends LeleException {
    public EmptyDescException(String message) {
        super(message);
    }
}
