package exceptions;

/**
 * This class is used to throw an exception when an attribute that is not part of the task is selected.
 */
public class InvalidAttributeException extends TaskGenieException {
    /**
     * Constructor for the InvalidAttributeException.
     * @param err The error.
     */
    public InvalidAttributeException(String task, Throwable err) {
        super("This is not a " + task + ".", err);
    }
}
