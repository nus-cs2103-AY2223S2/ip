package leo.leoexception;

/**
 * Represents an exception when no keyword input for search to happen.
 */
public class NoKeywordException extends LeoException {

    public NoKeywordException() {
        super("Oops! I do not know what I should be searching for...");
    }
}
