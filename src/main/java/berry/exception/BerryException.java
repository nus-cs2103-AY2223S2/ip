package berry.exception;

/**
 * Signals an exception associated with Berry.
 */
public class BerryException extends Exception {
    public BerryException(String errorMessage) {
        super(errorMessage);
    }
}