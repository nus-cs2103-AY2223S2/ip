package duke.exceptions;

/**
 * An error for when the task list is empty.
 */
public class StorerEmptyException extends DukeException {
    public StorerEmptyException() {
        super("☹ OOPS!!! I'm sorry, your list is empty :-(");
    }
}
