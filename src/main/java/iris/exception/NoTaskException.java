package iris.exception;

/**
 * encountered when the input is not a known command
 */
public class NoTaskException extends IrisException {
    public NoTaskException() {
        super("I'm sorry, I don't know what you mean.");
    }

}
