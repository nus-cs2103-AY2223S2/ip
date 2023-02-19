package treebot.exception;

/**
 * Signals an exception that occurs in the process of making <code>Task</code> objects by
 * the <code>TaskFactory</code>.
 *
 * This is the general exception produced in the process of making <code>Task</code> objects.
 */
public class TaskFactoryException extends TreeBotException {
    /**
     * Constructs a <code>TaskFactoryException</code> with the given error message.
     * @param errorMessage
     */
    public TaskFactoryException(String errorMessage) {
        super(errorMessage);
    }
}
