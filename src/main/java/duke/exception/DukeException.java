package duke.exception;

/**
 * Encapsulates the related fields and behavior of an exception specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Instantiates DukeException.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super("OPPS! " + message + "\nPlease try again.");
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
