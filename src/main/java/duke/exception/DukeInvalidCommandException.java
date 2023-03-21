package duke.exception;

/**
 * DukeInvalidCommandException is a type of DukeException that is thrown
 * when the user inputs an invalid command.
 */
public class DukeInvalidCommandException extends DukeException {
    public DukeInvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
