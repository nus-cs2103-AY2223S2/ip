package Exception;

/**
 * An exception to be thrown when met with an invalid input.
 * Extends from dukeException.
 */
public class invalidInputException extends dukeException{

    /**
     * Constructor for an invalidInputException object.
     *
     * @param errorMessage String to be used as error message.
     * @return invalidInputException object.
     */
    public invalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
