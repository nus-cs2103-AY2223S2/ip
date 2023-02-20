package kal.kalexception;

/**
 * This class handles exceptions pertaining to invalid commands.
 */
public class InvalidCommandException extends KalException {
    private static final String MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Creates an InvalidCommandException object using its default message.
     */
    public InvalidCommandException() {
        this(InvalidCommandException.MESSAGE);
    }

    /**
     * Creates an InvalidCommandException object.
     *
     * @param message The error message of this exception.
     */
    protected InvalidCommandException(String message) {
        super(message);
    }
}
