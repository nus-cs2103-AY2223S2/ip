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
        super(message);
    }

    /**
     * Returns the string representation of the error message to be printed.
     *
     * @return The error message string.
     */
    @Override
    public String toString() {
        return ("OPPS! " + super.getMessage() + "\nPlease try again.");
    }
}
