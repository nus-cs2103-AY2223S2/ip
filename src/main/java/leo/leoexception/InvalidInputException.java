package leo.leoexception;

/**
 * Represents an exception when input is not a supported feature.
 */
public class InvalidInputException extends LeoException {

    public InvalidInputException() {
        super("Ohno! I do not know what you mean...");
    }
}
