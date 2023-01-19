package exception;

public class InvalidCommandException extends TreeBotException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
