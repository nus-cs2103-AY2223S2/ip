package jarvis.exception.command;

/**
 * Exception from commands with insufficient parameters or information.
 */
public class MissingParameterException extends CommandParseException {
    public MissingParameterException(String message) {
        super(message, "I'm missing some information, could you please repeat?");
    }

    public MissingParameterException(String message, String friendlyMessage) {
        super(message, friendlyMessage);
    }
}
