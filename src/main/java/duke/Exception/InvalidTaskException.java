package duke.Exception;

/**
 * An InvalidTaskException a type of DukeException that is thrown when a certain valid command based on a task
 * number (mark, delete, unmark) takes in a task number of a task which does not yet exist/cannot exist.
 */
public class InvalidTaskException extends DukeException {

    /**
     * The constructor for an InvalidTaskException.
     * @param taskNumber The (invalid) task number input by the user.
     */
    public InvalidTaskException(int taskNumber) {
        super("Sorry, the task number " + taskNumber + " has not been added/cannot be added! Please try again");
    }

    @Override
    public String getExceptionType() {
        return "Invalid task number";
    }
}
