package duke.exceptions;

/**
 * Exception class specific to Deadlines and Events for not specifying a timeframe.
 */
public class UnspecTimeException extends Exception {
    public UnspecTimeException(String message) {
        super(message);
    }

    public UnspecTimeException(Throwable throwable) {
        super(throwable);
    }

    public UnspecTimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
