package duke.exceptions;

/**
 * Exception class specific to Deadlines and Events for not specifying a timeframe.
 */
public class unspecTimeException extends Exception {
    public unspecTimeException(String message) {
        super(message);
    }

    public unspecTimeException(Throwable throwable) {
        super(throwable);
    }

    public unspecTimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
