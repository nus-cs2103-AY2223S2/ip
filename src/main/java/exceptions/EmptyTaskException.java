package exceptions;

public class EmptyTaskException extends Exception {

    /**
     * Constructor for EmptyTaskException.
     * @param message The error message.
     */
    public EmptyTaskException(String message) {
        super(message);
    }
}
