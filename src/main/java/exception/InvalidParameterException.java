package exception;

public class InvalidParameterException extends CommandParseException {
    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
