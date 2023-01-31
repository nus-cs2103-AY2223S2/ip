package leo.leoexception;

/**
 * Represents an exception when input is not a supported feature.
 */
public class InvalidInputException extends LeoException {

    public InvalidInputException(String message) {
        super(message);
    }
}
