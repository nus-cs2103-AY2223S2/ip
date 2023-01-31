package duke.dukeExceptions;

public class DeadlineException extends DukeException {
    /**
     * Exception used by deadline task to indicate invalid user input when creating deadline task
     */

    public DeadlineException() {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}
