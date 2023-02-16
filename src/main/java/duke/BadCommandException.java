package duke;

/**
 * An exception class to manage bad commands.
 */
public class BadCommandException extends Exception {
    public BadCommandException(String errMsg) {
        super(errMsg);
    }
}
