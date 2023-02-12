package wessy.exceptions.int_exceptions;

import wessy.exceptions.WessyException;

/**
 * EmptyListException is an exception that should be thrown when the task list
 * is empty and yet the user requests for a task to be marked, unmarked or
 * deleted.
 */
public class EmptyListException extends WessyException {
    /**
     * Constructs an instance of EmptyListException.
     *
     * @param cmd The command requested by the user, in its String form.
     */
    public EmptyListException(String cmd) {
        super("You do not have any task on the list for you to " + cmd + ".");
    }
}
