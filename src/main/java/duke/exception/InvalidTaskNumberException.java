package duke.exception;

/**
 * A DukeException that deals with invalid task number inputs
 * when user enters a command that requires a task number
 */
public class InvalidTaskNumberException extends DukeException {
    /**
     * A constructor for InvalidTaskNumberException.
     *
     * @param taskNum task number input by user
     */
    public InvalidTaskNumberException(String taskNum) {
        super(String.format("Task number %s doesn't exist!", taskNum));
    }
}
