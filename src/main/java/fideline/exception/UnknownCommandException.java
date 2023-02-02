package fideline.exception;

/**
 * Informs the user that the given command does not exist.
 *
 * @author Fun Leon
 */
public class UnknownCommandException extends FidelineException {
    public UnknownCommandException() {
        super("this command does not exist!");
    }
}
