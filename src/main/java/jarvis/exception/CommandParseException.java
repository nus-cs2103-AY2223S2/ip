package jarvis.exception;

/**
 * Exception from parsing a command.
 */
public class CommandParseException extends JarvisException {
    public CommandParseException(String message) {
        super(message);
    }

    public CommandParseException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
