package exception;

/**
 * This exception is thrown when an instruction to add a Task is not followed by a description.
 */
public class NoDescriptionException extends Exception {

    public NoDescriptionException() {
        super("Need a description for your task.");
    }
}
