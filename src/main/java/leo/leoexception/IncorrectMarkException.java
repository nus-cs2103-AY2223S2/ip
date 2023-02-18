package leo.leoexception;

/**
 * Represents an exception when a marked Task is set to be marked.
 */
public class IncorrectMarkException extends LeoException {

    public IncorrectMarkException() {
        super("This task was already marked previously.");
    }
}
