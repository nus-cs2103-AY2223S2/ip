package berry.exception;

/**
 * Signals that some given data is not acceptable and does not fulfil certain constraints.
 */
public class IllegalValueException extends BerryException {

    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }
}
