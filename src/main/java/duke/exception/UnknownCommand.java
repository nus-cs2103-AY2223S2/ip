package duke.exception;

/**
 * Defines the exception in which the command input in the CLI is not known.
 */
public class UnknownCommand extends DukeException{
    public UnknownCommand(String message) {
        super(message);
    }
}
