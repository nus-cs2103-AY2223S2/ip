package dukeexceptions;

/**
 * An exception thrown when operations are performed on an empty <code>TaskList</code>.
 */
public class EmptyTaskListException extends DukeException {
    public EmptyTaskListException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "The Task List is currently empty!" + getMessage();
    }
}
