package duke.exception;

/**
 * Represents an exception that is thrown when the arguments given for a command are invalid.
 */
public class DukeInvalidDataFileException extends DukeException {

    public DukeInvalidDataFileException(String message) {
        super(message);
    }

}
