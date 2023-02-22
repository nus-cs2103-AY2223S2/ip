package exception;

/**
 * Thrown when an argument could not be parsed as a valid natural date component.
 */
public class NatDateParseException extends Exception {
    /**
     * Constructs a <code>NatTimeParseException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public NatDateParseException(String message) {
        super(message);
    }
}
