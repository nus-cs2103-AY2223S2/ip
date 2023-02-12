package wessy.exceptions.integer;

import wessy.exceptions.WessyException;

/**
 * InvalidIntegerException is an exception that should be thrown when the user
 * wants to mark, unmark or delete a task and the task number he specifies is
 * out of the upper bounds, given the total number of tasks on the list.
 */
public class InvalidIntegerException extends WessyException {
    /**
     * Constructs an instance of InvalidIntegerException.
     *
     * @param cmd The command requested by the user, in its String form.
     * @param taskNum The task number specified by the user.
     * @param total Total number of tasks on the list.
     */
    public InvalidIntegerException(String cmd, int taskNum, int total) {
        super(String.format("You can't %s Task %d as you only have %d tasks on"
                + " the list", cmd, taskNum, total));
    }
}
