package bob;

/**
 * Exception class that handles any errors related to Bob
 */
public class BobException extends Exception {
    public BobException(String message) {
        super("Error: " + message);
    }
}
