package duke.exceptions;

/**
 * Exception used when user does not specify keywords where needed
 */
public class MissingArgumentException extends DukeException {
    /**
     * Constructor for a MissingArgumentException.
     */
    public MissingArgumentException() {
        super("You're missing some arguments da yo...");
    }

    public MissingArgumentException(String message) {
        super(message);
    }
}
