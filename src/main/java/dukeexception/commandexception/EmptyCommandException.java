package dukeexception.commandexception;

/**
 * Exception to handle an empty user response.
 */
public class EmptyCommandException extends UnknownCommandException {
    public EmptyCommandException() {
        super();
    }
}
