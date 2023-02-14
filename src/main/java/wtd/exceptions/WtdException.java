package wtd.exceptions;

/**
 * WtdException is a custom exception class that extends the Exception class.
 */
public abstract class WtdException extends Exception {
    /**
     * Constructor for WtdException.
     * 
     * @param message the message to be displayed when the exception is thrown.
     */
    public WtdException(String message) {
        super("Stop messing around! " + message);
    }
}
