package exceptions;

/**
 * This class is used to throw an exception when there is no date.
 */
public class NoDateException extends TaskGenieException {
    /**
     * Constructor for the NoDateException.
     * @param task The task that has no date.
     * @param err The error.
     */
    public NoDateException(String task, Throwable err) {
        super("The date/time of the " + task + " cannot be empty.", err);
    }
}
