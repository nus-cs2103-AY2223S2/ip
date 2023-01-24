package duke.task;

/**
 * Represents a todo task.
 *
 * @author wz2k
 */
public class Todo extends Task {
    /**
     * Creates a todo task.
     *
     * @param desc Description of the todo task.
     * @param marked Task marking.
     */
    public Todo(String desc, boolean marked) {
        super(desc, marked);
    }

    /**
     * Returns the task type, checkbox and description.
     *
     * @return Todo task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string to store the todo task in a file.
     *
     * @return File storage version of the todo task.
     */
    @Override
    public String toTaskStorageString() {
        return "T" + "|" + super.toTaskStorageString();
    }
}
