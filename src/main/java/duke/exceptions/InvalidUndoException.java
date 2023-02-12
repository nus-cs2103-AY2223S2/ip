package duke.exceptions;

/**
 * InvalidUndoException represents an exception if
 * are no commands to undo.
 */
public class InvalidUndoException extends Exception {
    /**
     * Creates a InvalidUndoException
     */
    public InvalidUndoException() {
        super("OOPS!!! I'm sorry, but there is nothing to undo.");
    }
}
