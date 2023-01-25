package duke;

/**
 * DukeException is a custom exception class that extends the Exception class.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * 
     * @param message the message to be displayed when the exception is thrown.
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
