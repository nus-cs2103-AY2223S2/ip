package botanic.exception;

/**
 * Encapsulates the related fields and behavior of an InvalidInputException.
 * Represents the exception to throw when given input is invalid,
 * for instance, command given does not exit.
 */
public class InvalidInputException extends BotanicException {
    /**
     * Instantiates InvalidInputException.
     *
     * @param message The error message.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
