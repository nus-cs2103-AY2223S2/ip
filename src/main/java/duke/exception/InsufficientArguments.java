package duke.exception;

/**
 * Defines the exception in which insufficient arguments are supplied to a command in CLI.
 */
public class InsufficientArguments extends DukeException {
    public InsufficientArguments(String message) {
        super(message);
    }
}

