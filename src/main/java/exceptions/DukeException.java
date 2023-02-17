package exceptions;

/**
 * Represents an exception that is thrown when an error occurs within the application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param err the detail message of the exception.
     */
    public DukeException(String err) {
        super(err);
    }

}
