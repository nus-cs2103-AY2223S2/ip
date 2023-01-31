package elise;

/**
 * Represent exceptions specific to Elise.
 */
public class EliseException extends Exception {
    /**
     * Constructor for EliseException.
     *
     * @param message Error message.
     */
    protected EliseException(String message) {
        super(message);
    }
}
