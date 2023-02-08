package red.exception;

/**
 * This class specifies the exceptions that arise when the Red program is running.
 */

public class RedException extends RuntimeException{
    public RedException(String errMessage) {
        super(errMessage);
    }
}
