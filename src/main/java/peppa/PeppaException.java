package peppa;

/**
 * Represents a chatbot-specific exception.
 */
public class PeppaException extends Exception {
    public PeppaException() {}
    public PeppaException(String message) {
        super(message);
    }
}
