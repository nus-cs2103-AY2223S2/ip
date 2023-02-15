package duke.exception;

/**
 * Represents error when clearing empty task list
 */
public class EmptyTaskListException extends DukeException {
    public EmptyTaskListException() {
        super("WOOF! Boss! Get the Minions to double check! The task list is empty :(");
    }
}
