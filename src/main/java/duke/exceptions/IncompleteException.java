package duke.exceptions;

/**
 * An error for incomplete arguments.
 */
public class IncompleteException extends DukeException {
    public IncompleteException() {
        super("OOPS!!! I'm sorry, but your arguments are incomplete!");
    }
}
