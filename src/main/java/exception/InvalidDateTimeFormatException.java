package exception;

/**
 * Signals an exception when an invalid format for <code>DateTime</code> is given in the process
 * of making <code>Task</code> objects in the <code>TaskFactory</code>.
 */
public class InvalidDateTimeFormatException extends TaskFactoryException {

    /**
     * Constructs an <code>InvalidDateTimeException</code> with the given error message.
     * @param errorMessage
     */
    public InvalidDateTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
