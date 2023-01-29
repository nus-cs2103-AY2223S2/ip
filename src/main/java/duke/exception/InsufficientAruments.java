package duke.exception;

/**
 * Defines the exception in which insufficient arguments are supplied to a command in CLI.
 */
public class InsufficientAruments extends DukeException {
    public InsufficientAruments(String message) {
        super(message);
    }
}

