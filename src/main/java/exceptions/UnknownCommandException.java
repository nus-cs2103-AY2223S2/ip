package exceptions;

/**
 * Represents exceptions for commands not known by the chatbot.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructs an exception for a command not known by the chatbot.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
