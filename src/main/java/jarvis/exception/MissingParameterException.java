package jarvis.exception;

/**
 * Exception from commands with insufficient parameters or information.
 */
public class MissingParameterException extends CommandParseException {
    public MissingParameterException(String message) {
        super(message);
    }

    public MissingParameterException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
