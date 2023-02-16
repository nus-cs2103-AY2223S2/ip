package duke.exception;

/**
 * A DukeException that deals with incorrect commands input
 * by a user.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for InvalidCommandException.
     */
    public InvalidCommandException() {
        super("I'm sorry, I don't understand what that means :-(\n");
    }
}
