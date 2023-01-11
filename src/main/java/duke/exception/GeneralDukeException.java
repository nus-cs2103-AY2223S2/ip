package duke.exception;

/**
 * An abstract Exception class encapsulating an exception in Duke, which can be extended
 * by more specific exceptions like invalidInputException
 */

public class GeneralDukeException extends Exception{
    public GeneralDukeException(String errorMessage) {
        super(errorMessage);
    }
}
