package bob;

public class BobException extends Exception {
    public BobException(String message) {
        super("Error: " + message);
    }
}
