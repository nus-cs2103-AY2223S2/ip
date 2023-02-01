package duke.exceptions;

/**
 * An error for invalid arguments.
 */
public class InvalidException extends DukeException {
    public InvalidException() {
        super("OOPS!!! I'm sorry, but your arguments are invalid!");
    }
}
