package fideline.exception;

/**
 * Informs user that empty input is not allowed.
 *
 * @author Fun Leon
 */
public class EmptyInputException extends FidelineException {

    public EmptyInputException() {
        super("hello?? can you say something?");
    }
}
