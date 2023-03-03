package cluck.exceptions;

/**
 * Incorrect argument exception is thrown when argument given is in the wrong format.
 */
public class IncorrectArgumentException extends CluckException {
    /**
     * Instantiates a new Incorrect argument exception.
     *
     * @param errorMessage the error message
     */
    public IncorrectArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
