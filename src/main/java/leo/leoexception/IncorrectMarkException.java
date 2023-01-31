package leo.leoexception;

/**
 * Represents an exception when a marked Task is set to be marked.
 */
public class IncorrectMarkException extends LeoException {

    public IncorrectMarkException(String message) {
        super(message);
    }
}
