package fideline.exception;

/**
 * Informs user that the task cannot be created as it
 * already exists.
 *
 * @author Fun Leon
 */
public class DuplicateTaskException extends FidelineException {

    public DuplicateTaskException() {
        super("this task already exists!");
    }
}
