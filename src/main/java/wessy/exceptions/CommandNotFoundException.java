package wessy.exceptions;

/**
 * CommandNotFoundException is an exception that should be thrown when the user
 * inputs some text that could not be recognised as a command.
 */
public class CommandNotFoundException extends WessyException {
    /**
     * Constructs an instance of CommandNotFoundException.
     */
    public CommandNotFoundException() {
        super("I'm sorry, but I don't know what that means.");
    }
}
