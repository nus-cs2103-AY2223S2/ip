package dukeexceptions;

/**
 * An exception thrown when a <code>Task</code> outside the bounds of a <code>TaskList</code> is being accessed
 */
public class TaskListIndexOutOfBoundsException extends DukeException {
    public TaskListIndexOutOfBoundsException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Sorry, the requested index is out of bounds of the TaskList" + getMessage();
    }
}
