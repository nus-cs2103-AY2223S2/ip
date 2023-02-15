package membot.utils;

/**
 * Represents an <code>Exception</code> when the input is empty when it is
 * not supposed to be.
 */
public class EmptyInputException extends Exception {
    public EmptyInputException(String msg) {
        super(msg);
    }
}
