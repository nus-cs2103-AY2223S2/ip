package jarvis.exception;

public class InvalidActionException extends CommandParseException {
    public InvalidActionException(String message) {
        super(message);
    }
}
