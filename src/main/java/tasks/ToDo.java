package tasks;

/**
 * Represents a simple task that can be completed.
 */
public class ToDo extends Task {
    /**
     * Construct a new task that can be completed.
     *
     * @param description Description of the task.
     * @param priority Priority of the task.
     */
    public ToDo(String description, int priority) {
        super(description, priority);
    }

    @Override
    public String toEncodedString() {
        return "[" + TaskType.T + "]" + super.toString();
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }
}
