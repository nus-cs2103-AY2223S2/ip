package jarvis.exception;

/**
 * Exception from commands with invalid or unknown parameters.
 */
public class InvalidParameterException extends CommandParseException {
    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
