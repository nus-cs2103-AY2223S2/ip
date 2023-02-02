package fideline.exception;

/**
 * Informs user that the command called requires parameters that was
 * not provided.
 *
 * @author Fun Leon
 */
public class EmptyParamException extends FidelineException {

    public EmptyParamException() {
        super("this command cannot have an empty description!");
    }
}
