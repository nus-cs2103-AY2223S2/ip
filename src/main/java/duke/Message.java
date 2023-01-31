package duke;

/**
 * POJO representing the messages passed between user and the chatbot within internal systems.
 */
public class Message {

    private final String message;

    /**
     * Constructs a <code>Message</code> with given contents.
     * @param message message to be passed
     */
    public Message(String message) {
        this.message = message;
    }

    /**
     * Returns the <code>String</code> message contained in the object
     * @return <code>String</code> passed message
     */
    public String getMessage() {
        return message;
    }
}
