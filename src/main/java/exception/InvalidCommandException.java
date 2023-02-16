package exception;

/**
 * Signals an exception that occurs when an invalid command is given.
 */
public class InvalidCommandException extends TreeBotException {
    /**
     * Constructs an <code>InvalidCommandException</code> with the given error message.
     * @param errorMessage
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
