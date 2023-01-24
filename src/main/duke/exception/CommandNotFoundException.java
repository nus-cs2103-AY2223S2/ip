package exception;

public class CommandNotFoundException extends DukeException{
    String command;

    public CommandNotFoundException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
