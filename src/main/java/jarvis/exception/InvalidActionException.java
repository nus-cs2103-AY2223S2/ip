package jarvis.exception;

/**
 * Exception from commands with invalid action verbs.
 */
public class InvalidActionException extends CommandParseException {
    public InvalidActionException(String message) {
        super(message);
    }
}
