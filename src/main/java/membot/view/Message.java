package membot.view;

/**
 * Message encapsulates the message that Membot outputs to the user.
 */
public class Message {
    private final String message;
    private final boolean isError;

    /**
     * Generates a <code>Message</code> object.
     *
     * @param message The message to be outputted to the user.
     * @param isError True if it is an error message, false otherwise.
     */
    public Message(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Returns the messgae encapsulated by this <code>Message</code> object.
     *
     * @return The messgae encapsulated.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns whether the message is an error message.
     *
     * @return True if it is an error message, false otherwise.
     */
    public boolean isError() {
        return this.isError;
    }
}
