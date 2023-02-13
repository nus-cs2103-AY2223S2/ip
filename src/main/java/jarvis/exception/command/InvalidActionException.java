package jarvis.exception.command;

/**
 * Exception from commands with invalid action verbs.
 */
public class InvalidActionException extends CommandParseException {
    public InvalidActionException(String message) {
        super(message, "I don't understand what to do, please try again.");
    }
}
