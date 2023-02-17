package exception;

/**
 * An exception to be thrown when met with an invalid input.
 * Extends from DukeException.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructs an InvalidInputException object.
     *
     * @param errorMessage String to be used as error message.
     * @return InvalidInputException object.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
