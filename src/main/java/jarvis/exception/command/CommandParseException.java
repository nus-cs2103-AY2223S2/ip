package jarvis.exception.command;

import jarvis.exception.JarvisException;

/**
 * Exception from parsing a command.
 */
public class CommandParseException extends JarvisException {
    public CommandParseException(String message) {
        super(message, "That's Greek to me.");
    }

    public CommandParseException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
