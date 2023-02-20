package duke.exception;

/**
 * A class to represent an empty description instantiation.
 */
public class EmptyDescriptionException extends Error {
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of a task cannot be empty :-(");
    }
}
