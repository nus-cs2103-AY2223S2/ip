package membot.utils;

/**
 * Represents an <code>Exception</code> when no start dateTime can be found when it is
 * supposed to be.
 */
public class NoStartDateTimeFoundException extends Exception {
    public NoStartDateTimeFoundException(String msg) {
        super(msg);
    }
}
