package jarvis.exception;

public class JarvisException extends Exception {
    private final String friendlyMessage;

    public JarvisException(String message) {
        super(message);
        this.friendlyMessage = null;
    }

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
