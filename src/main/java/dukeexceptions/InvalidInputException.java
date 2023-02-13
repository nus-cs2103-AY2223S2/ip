package dukeexceptions;

/**
 * Encapsulates an Exception when the input is invalid.
 */
public class InvalidInputException extends DukeException {

    /**
     * Constructor.
     *
     */
    public InvalidInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
