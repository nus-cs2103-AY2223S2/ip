package lele.exception;

/**
 * An exception that happens when user tries to undo past the program's history.
 */
public class NoPreviousCommandException extends LeleException {
    public NoPreviousCommandException(String message) {
        super(message);
    }
}
