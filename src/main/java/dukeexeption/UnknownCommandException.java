package dukeexeption;

/**
 * Exception when the user input an unknown argument in the command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

    public UnknownCommandException(String message) {
        super(message);
    }
}
