package duke.exceptions;

/**
 * General exception class for displaying error messages to users.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException that stores an error message.
     * @param message duke.Main body of error message
     */
    public DukeException(String message) {
        super(message);
    }
}
