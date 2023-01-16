package duke.exception;

/**
 * An abstract Exception class encapsulating an exception in Duke, which can be extended
 * by more specific exceptions like invalidInputException
 */

public class DukeException extends Exception {
    /**
     * Constructor for DukeException with the error message to be printed.
     *
     * @param errorMessage  The error message to be printed
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
