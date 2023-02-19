package fideline.exception;

/**
 * Informs the user that the command was not executed
 * because it was given it takes no arguments, but one or
 * more arguments were given.
 *
 * @author Fun Leon
 */
public class UnexpectedArgumentException extends FidelineException {

    /**
     * Constructs exception that informs the user that the command given
     * accepts no arguments
     */
    public UnexpectedArgumentException() {
        super("no arguments expected for this command!");
    }
}
