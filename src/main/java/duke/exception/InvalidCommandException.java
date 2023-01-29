package duke.exception;

public class InvalidCommandException extends DukeException {
    /**
     * Constructor for InvalidCommandException.
     */
    public InvalidCommandException() {
        super("I'm sorry, I don't understand what that means :-(\n");
    }
}
