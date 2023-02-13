package chad.exception;

/**
 * Exception for when users keyed in an unknown command.
 * duke.Duke doesn't know what to do.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructor to create an exception for unknown command
     */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
