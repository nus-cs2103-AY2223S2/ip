package duke.dukeExceptions;

public class TodoException extends DukeException {
    /**
     * Exception used by todo task to indicate invalid user input when creating todo task
     */

    public TodoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}