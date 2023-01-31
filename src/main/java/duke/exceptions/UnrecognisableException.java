package duke.exceptions;
/**
 * An error for an empty or unrecognisable input.
 */
public class UnrecognisableException extends DukeException {
    public UnrecognisableException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}