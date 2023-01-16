package exceptions;

public class IncompleteCommandException extends InvalidCommandException {
    public IncompleteCommandException(String cmd, String content) {
        super("☹ OOPS!!! The " + content + " of a " + cmd + " cannot be empty");
    }
}
