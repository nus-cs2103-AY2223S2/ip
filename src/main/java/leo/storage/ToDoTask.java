package leo.storage;

/**
 * Represents a ToDoTask. A <code>ToDoTask</code> object corresponds to
 * a Task containing the String description.
 */
public class ToDoTask extends Task {

    /**
     * Constructor for creating a ToDoTask object.
     *
     * @param task Description of the Task.
     */
    public ToDoTask(String task) {
        super(task);
        setType(TaskType.TODO);
    }

    /**
     * Returns type, status, description and deadline of Task.
     *
     * @return String representation of ToDoTask.
     */
    @Override
    public String toString() {
        return typeAndStatus() + getTask();
    }

    /**
     * Returns the String representation of Task that is to be saved in the data file.
     *
     * @return String representation of ToDoTask.
     */
    @Override
    public String saveFormat() {
        return this + "\n";
    }
}
