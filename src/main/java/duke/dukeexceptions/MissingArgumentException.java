package duke.dukeexceptions;

/**
 * Exception when the user is missing arguments in the command.
 */
public class MissingArgumentException extends DukeExceptions {
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
