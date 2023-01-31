package duke.exception;

/**
 * A custom exception for the Duke program.
 * It denotes an invalid argument from the user,
 * e.g. a date or task number that does not exist.
 */
public class DukeInvalidArgumentException extends DukeException {

    public DukeInvalidArgumentException(String msg) {
        super(msg);
    }

}
