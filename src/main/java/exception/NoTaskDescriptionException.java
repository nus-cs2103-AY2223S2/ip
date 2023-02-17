package exception;

/**
 * Exception to be thrown when the specific scenario of no task description given as arguments.
 * Inherits from DukeException.
 */
public class NoTaskDescriptionException extends DukeException {
    /**
     * Constructs a NoTaskDescriptionException object.
     *
     * @param errorMessage String to be the error message.
     * @return NoTaskDescriptionException object.
     */
    public NoTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
