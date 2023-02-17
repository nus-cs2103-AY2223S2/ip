package duke.exception;

/**
 * Custom exception for errors that occur during execution
 *
 * @author Guo-KeCheng
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Duke Exception class constructor
     * Instantiate message with the given message
     *
     * @param message
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Gets the String representation of message
     *
     * @return String representation of message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
