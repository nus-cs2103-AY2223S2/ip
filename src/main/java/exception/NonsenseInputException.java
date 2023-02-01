package exception;

/**
 * This exception is thrown when the user input cannot be recognized by Babe.
 */
public class NonsenseInputException extends Exception {

    public NonsenseInputException() {
        super("I do not know how to read this. SORRRYY </3");
    }
}
