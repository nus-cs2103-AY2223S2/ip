package exceptions;

/**
 * Exception for when Duke encounters a commands with an incomplete command.
 * Exception also thrown when Duke receives a command with incomplete information.
 */
public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
