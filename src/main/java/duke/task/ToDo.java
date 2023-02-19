package duke.task;

/**
 * Represents a to-do task that has a description.
 */
public class ToDo extends Task {
    /**
     * Creates a to-do object with the given task description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do.
     *
     * @return A string representation of the to-do,
     *     with the format "[T][X] Task description" if it is done,
     *     and "[T][ ] Task description" otherwise.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskState() {
        return "T | " + super.getTaskString();
    }
}
