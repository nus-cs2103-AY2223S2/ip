package Exception;

/**
 * Exception to be thrown when the specific scenario of no task description given as arguments.
 * Inherits dukeException.
 */
public class noTaskDescriptionException extends dukeException {

    /**
     * Constructor for a noTaskDescriptionException object.
     *
     * @param errorMessage String to be the error message.
     * @return noTaskDescriptionException object.
     */
    public noTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
    
}
