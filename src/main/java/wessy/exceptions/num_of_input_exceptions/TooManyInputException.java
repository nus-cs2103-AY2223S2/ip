package wessy.exceptions.num_of_input_exceptions;

import wessy.exceptions.WessyException;

/**
 * TooManyInputException is an exception that should be thrown when the user
 * wants to mark, unmark or delete a task, and he enters more than one integer
 * to specify the task numbers.
 */
public class TooManyInputException extends WessyException {
    /**
     * Constructs an instance of TooManyInputException.
     *
     * @param cmd The command requested by the user, in its String form.
     */
    public TooManyInputException(String cmd) {
        super(String.format("The '%s' command only takes in 1 input.", cmd));
    }

}
