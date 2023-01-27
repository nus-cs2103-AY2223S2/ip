package app.command;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
