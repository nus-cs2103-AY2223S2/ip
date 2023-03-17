package dukeexception.commandexception;

/**
 * Exception to handle an empty user response.
 */
public class EmptyCommandException extends UnknownCommandException {
    /**
     * Constructor for EmptyCommandException.
     */
    public EmptyCommandException() {
        super();
    }
}
