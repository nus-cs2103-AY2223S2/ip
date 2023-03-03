package duke.task;

/**
 * The ToDo task which stores the task description.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
