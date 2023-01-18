package utils;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(String.format("[Invalid command] %s", msg));
    }
}
