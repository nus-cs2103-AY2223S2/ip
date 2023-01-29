package dukeexeption;

/**
 * Exception when the user is missing an argument in the command.
 */
public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
