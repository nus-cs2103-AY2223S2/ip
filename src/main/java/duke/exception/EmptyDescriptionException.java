package duke.exception;

/**
 * Exception thrown when there is no description after the command
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }

}
