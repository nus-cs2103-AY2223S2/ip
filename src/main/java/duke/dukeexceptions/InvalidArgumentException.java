package duke.dukeexceptions;

/**
 * Exception when the user inputs an invalid argument in the command.
 */
public class InvalidArgumentException extends DukeExceptions {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}