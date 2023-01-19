/**
 * DukeInvalidArgumentException indicates that the user-supplied command has a correct
 * syntax, but the argument/s is not allowed, has an invalid syntax, or the wrong number
 * of arguments is supplied corresponding to the command.
 */
public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
