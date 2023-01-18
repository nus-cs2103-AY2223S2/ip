package jarvis.exception;

public class CommandParseException extends JarvisException {
    public CommandParseException(String message) {
        super(message);
    }

    public CommandParseException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
