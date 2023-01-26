package jarvis.exception;

/**
 * Base exception from Jarvis.
 */
public class JarvisException extends Exception {
    private final String friendlyMessage;

    /**
     * Constructor for a Jarvis exception.
     *
     * @param message Error message.
     */
    public JarvisException(String message) {
        super(message);
        this.friendlyMessage = null;
    }

    /**
     * Constructor for a Jarvis exception, with a friendly message.
     *
     * @param message Error message.
     * @param friendlyMessage User-facing friendly message.
     */
    public JarvisException(String message, String friendlyMessage) {
        super(message);
        this.friendlyMessage = friendlyMessage;
    }

    public String getFriendlyMessage() {
        return this.friendlyMessage == null || this.friendlyMessage.isBlank()
                ? this.getMessage()
                : this.friendlyMessage;
    }
}
