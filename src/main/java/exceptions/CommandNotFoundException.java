package exceptions;

public class CommandNotFoundException extends WessyException {
    public CommandNotFoundException() {
        super("I'm sorry, but I don't know what that means.");
    }
}
