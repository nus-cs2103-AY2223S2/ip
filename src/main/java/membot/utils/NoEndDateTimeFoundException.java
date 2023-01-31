package membot.utils;

/**
 * Represents an <code>Exception</code> when no end dateTime can be found when it is
 * supposed to be.
 */
public class NoEndDateTimeFoundException extends Exception {
    public NoEndDateTimeFoundException(String msg) {
        super(msg);
    }
}
