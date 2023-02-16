package exception;

/**
 * Signals an exception that occurs when task description is not given in the process
 * of making <code>Task</code> objects by the <code>TaskFactory</code>.
 */
public class MissingTaskDescriptionException extends TaskFactoryException {

    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
