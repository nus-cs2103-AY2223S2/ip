package duke.task;

/**
 * Represents a todo task.
 *
 * @author wz2k
 */
public class Todo extends Task {
    /**
     * Constructor for duke.task.Todo class.
     *
     * @param description description of the todo task.
     */
    public Todo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    /**
     * This method returns the task type, checkbox and description.
     *
     * @return todo task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTaskStorageString() {
        return "T" + "|" + super.toTaskStorageString();
    }
}
