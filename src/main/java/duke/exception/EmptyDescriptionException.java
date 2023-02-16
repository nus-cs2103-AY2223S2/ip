package duke.exception;

/**
 * Exception thrown when there is no description after the command
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructor for EmptyDescriptionException
     */
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty while adding a task! "
                + "\n If you want to mark/ unmark/ delete task, please enter task number after command!");
    }

}
