package leo.leoexception;

/**
 * Represents an exception when no Tasks in found.
 */
public class NoTaskFoundException extends LeoException {

    public NoTaskFoundException() {
        super("There are no matching tasks!");
    }
}
