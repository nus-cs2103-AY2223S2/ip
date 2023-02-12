package wessy.exceptions.int_exceptions;

import wessy.exceptions.WessyException;

/**
 * NotPositiveIntegerException is an exception that should be thrown when the
 * user wants to mark, unmark or delete a task, and he specifies a non-positive
 * integer. Remember that the task list starts from 1 instead of 0, in terms of
 * user's input.
 */
public class NotPositiveIntegerException extends WessyException {
    /**
     * Constructs an instance of NotPositiveIntegerException.
     */
    public NotPositiveIntegerException() {
        super("The number you just input is not a positive integer. Please " +
                "input a positive integer.");
    }
}
