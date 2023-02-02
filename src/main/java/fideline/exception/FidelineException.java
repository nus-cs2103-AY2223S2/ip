package fideline.exception;

/**
 * Signals that Fideline has found an issue. This is the parent class
 * to all custom Fideline exceptions.
 *
 * @author Fun Leon
 */
public class FidelineException extends Exception {

    /**
     * Constructs an exception that informs the user of an issue
     * occurring during the program.
     *
     * @param errorMessage Message describing the exception.
     */
    public FidelineException(String errorMessage) {
        super(errorMessage);
    }
}
