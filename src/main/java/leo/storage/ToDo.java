package leo.storage;

/**
 * Represents a ToDo Task. A <code>ToDo</code> object corresponds to
 * a Task containing the String description.
 */
public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
        setType(TaskType.TODO);
    }

    /**
     * Returns type, status, description and deadline of Task.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return typeAndStatus() + getTask();
    }

    /**
     * Returns the String representation of Task that is to be saved in the data file.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String saveFormat() {
        return this + "\n";
    }
}
