package leo.leoexception;

/**
 * Represents an exception when no deadline is indicated in a Deadline Task.
 */
public class EmptyDeadlineException extends LeoException {

    public EmptyDeadlineException(String message) {
        super(message);
    }
}
