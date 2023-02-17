package exceptions;

/**
 * Duke exception when the users did not enter all required fields for the command
 */
public class EmptyTaskException extends DukeException {
    public EmptyTaskException(String err) {
        super(err);
    }
}
