package chad.exceptions;

/**
 * Parent class for all Duke-internal exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a Duke exception.
     * @param msg The exception message.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
