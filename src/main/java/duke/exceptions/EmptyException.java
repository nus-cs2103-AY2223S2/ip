package duke.exceptions;

/**
 * An error for an empty input.
 */
public class EmptyException extends DukeException {
    public EmptyException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}