package exceptions;

/**
 * This class is used to throw an exception when running TaskGenie.
 */
public abstract class TaskGenieException extends RuntimeException {
    /**
     * Constructor for the TaskGenieException.
     * @param errorMessage The error message to be printed.
     * @param err The error.
     */
    public TaskGenieException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
