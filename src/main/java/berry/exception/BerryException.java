package berry.exception;

/**
 * Signals an exception associated with Berry.
 */
public class BerryException extends Exception {
    /**
     * Initialises {@code BerryException}
     *
     * @param errorMessage Message to be printed by Berry to show error.
     */
    public BerryException(String errorMessage) {
        super(errorMessage);
    }
}
