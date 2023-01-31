package duke.exception;

/**
 * Represents an exception that is thrown when the arguments given for a command are invalid.
 */
public class DukeInvalidArgumentException extends DukeException {

    public DukeInvalidArgumentException(String message) {
        super(message);
    }

}
