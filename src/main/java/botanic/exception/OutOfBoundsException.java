package botanic.exception;

/**
 * Encapsulates the related fields and behavior of OutOfBoundsException.
 * Represents the exception to throw when indexes given are out of bounds.
 */
public class OutOfBoundsException extends BotanicException {
    /**
     * Instantiates OutOfBoundsException.
     *
     * @param message The error message.
     */
    public OutOfBoundsException(String message) {
        super(message);
    }
}
