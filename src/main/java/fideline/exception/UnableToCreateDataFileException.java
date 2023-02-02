package fideline.exception;

/**
 * Informs the user that the program is unable to run, due to
 * being unable to create a data file.
 *
 * @author Fun Leon
 */

public class UnableToCreateDataFileException extends FidelineException {
    public UnableToCreateDataFileException(String errorMessage) {
        super(errorMessage);
    }
}
