package duke.exception;

/**
 * Represents an exception that is thrown when an invalid command is entered.
 */
public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException(String message) {
        super(message);
    }

}
