package exception;

/**
 *  Exception to be thrown when the specific scenario of no expense description given as arguments.
 *  Inherits from DukeException.
 */
public class NoExpenseParameterException extends DukeException {
    /**
     * Constructs a NoTaskDescriptionException object.
     *
     * @param errorMessage String to be the error message.
     * @return NoExpenseParameterException object.
     */
    public NoExpenseParameterException(String errorMessage) {
        super(errorMessage);
    }
}
