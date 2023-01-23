package duke.exceptions;

public class emptyDescException extends Exception {
    public emptyDescException(String message) {
        super(message);
    }

    public emptyDescException(Throwable throwable) {
        super(throwable);
    }

    public emptyDescException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
