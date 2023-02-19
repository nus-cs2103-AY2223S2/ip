package duke.exception;

/**
 * DukeInvalidArgumentException is a type of DukeException that is thrown
 * when an invalid argument is passed to a Duke command.
 */
public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
