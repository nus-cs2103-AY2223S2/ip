package exception;

/**
 * This exception is thrown when the user input cannot be recognized by Babe.
 */
public class NonsenseInputException extends Exception {

    public NonsenseInputException() {
        super("User input makes no sense.");
    }
}
