package dukeexeption;

/**
 * Exception when the user inputs an invalid argument in the command.
 */
public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
