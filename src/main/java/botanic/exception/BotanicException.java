package botanic.exception;

/**
 * Encapsulates the related fields and behavior of an exception specific to Botanic.
 */
public class BotanicException extends Exception {
    /**
     * Instantiates BotanicException.
     *
     * @param message The error message.
     */
    public BotanicException(String message) {
        super("Oh noes! " + message + "\nPlease try again.");
    }

    /**
     * Returns the string representation of the error message to be printed.
     *
     * @return The error message string.
     */
    @Override
    public String toString() {
        return (super.getMessage());
    }
}
