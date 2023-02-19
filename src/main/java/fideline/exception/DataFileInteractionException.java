package fideline.exception;

/**
 * Informs user that the program is unable to interact with
 * the data file.
 *
 * @author Fun Leon
 */
public class DataFileInteractionException extends FidelineException {

    /**
     * Constructs exception informing user that there is an issue with
     * interacting with the data file.
     *
     * @param errorMessage Message explaining probable cause of the exception.
     */
    public DataFileInteractionException(String errorMessage) {
        super(errorMessage);
    }
}
