package exception;

/**
 * Represents the exception where the command given is not valid.
 */
public class CommandNotFoundException extends DukeException{
    String command;

    public CommandNotFoundException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
