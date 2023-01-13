package duke.exception;

/**
 * An abstract Exception class encapsulating an exception in Duke, which can be extended
 * by more specific exceptions like invalidInputException
 */

public class GeneralDukeException extends Exception{
    /**
     * Constructor for GeneralDukeException with the error message to be printed.
     *
     * @param errorMessage  The error message to be printed
     */
    public GeneralDukeException(String errorMessage) {
        super(errorMessage);
    }
}
