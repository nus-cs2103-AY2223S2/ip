package exceptions;

/**
 * This class is used to throw an exception when there is no description.
 */
public class NoDescriptionException extends TaskGenieException {
    /**
     * Constructor for the NoDescriptionException.
     * @param task The task that has no description.
     * @param err The error.
     */
    public NoDescriptionException(String task, Throwable err) {
        super("OOPS!!! The description of a " + task + " cannot be empty.", err);
    }
}
