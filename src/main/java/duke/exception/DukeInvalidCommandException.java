package duke.exception;

/**
 * A custom exception for the Duke program.
 * It denotes an invalid command from the user,
 * e.g. wrong format, invalid keyword.
 */
public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException() {
        super("Hmm, your command format's a little off!");
    }
    public DukeInvalidCommandException(String msg) {
        super(msg);
    }

}
