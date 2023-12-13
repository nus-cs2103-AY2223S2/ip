package botanic.exception;

/**
 * Encapsulates the related fields and behavior of an InvalidInputException.
 * This class represents the exception to throw when given input is invalid,
 * for instance, given command given does not exit.
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
