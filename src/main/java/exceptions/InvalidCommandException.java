package exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException (String str) {
        super(str);
    }
}
