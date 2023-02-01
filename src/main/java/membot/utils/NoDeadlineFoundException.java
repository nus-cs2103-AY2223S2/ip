package membot.utils;

/**
 * Represents an <code>Exception</code> when no deadline can be found when it is
 * supposed to be.
 */
public class NoDeadlineFoundException extends Exception {
    public NoDeadlineFoundException(String msg) {
        super(msg);
    }
}
