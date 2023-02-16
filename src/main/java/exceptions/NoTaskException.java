package exceptions;

/**
 * This class is used to throw an exception when there is no task in the database.
 */
public class NoTaskException extends TaskGenieException {
    /**
     * Constructor for the NoTaskException.
     * @param err The error.
     */
    public NoTaskException(Throwable err) {
        super("OOPS!!! I'm sorry, but the list is empty currently!", err);
    }
}
