package lele.exception;

/**
 * An exception when a command isn't valid.
 */
public class UnknownCommandException extends LeleException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
