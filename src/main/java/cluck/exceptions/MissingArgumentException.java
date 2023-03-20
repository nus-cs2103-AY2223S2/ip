package cluck.exceptions;

/**
 * Missing argument exception is thrown when arguments are missing from the user input and
 * Command cannot be instantiated as a result.
 */
public class MissingArgumentException extends CluckException {
    /**
     * Instantiates a new Missing argument exception.
     *
     * @param errorMessage The error message.
     */
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
