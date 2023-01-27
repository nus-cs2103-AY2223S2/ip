package leo.leoException;

/**
 * Represents an exception when no Tasks in found.
 */
public class NoTaskFoundException extends LeoException {

    public NoTaskFoundException(String message) {
        super(message);
    }
}
