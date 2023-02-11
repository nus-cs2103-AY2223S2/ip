package duke.task;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Initialises the task with the task name.
     *
     * @param description Name of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    @Override
    public String toSaveString() {
        return "T$$$" + super.toSaveString();
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
