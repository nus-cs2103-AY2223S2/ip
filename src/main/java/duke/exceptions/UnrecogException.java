package duke.exceptions;

/**
 * Exception class for commands not recognised or supported by Duke bot.
 */
public class UnrecogException extends Exception {
    public UnrecogException(String message) {
        super(message);
    }

    public UnrecogException(Throwable throwable) {
        super(throwable);
    }

    public UnrecogException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
