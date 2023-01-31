package leo.leoexception;

/**
 * Represents an exception when no keyword input for search to happen.
 */
public class NoKeywordException extends LeoException {

    public NoKeywordException(String message) {
        super(message);
    }
}
