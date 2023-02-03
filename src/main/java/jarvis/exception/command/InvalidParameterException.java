package jarvis.exception.command;

/**
 * Exception from commands with invalid or unknown parameters.
 */
public class InvalidParameterException extends CommandParseException {
    public InvalidParameterException(String message) {
        super(message, "I don't understand the given information, please try again.");
    }

    public InvalidParameterException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
