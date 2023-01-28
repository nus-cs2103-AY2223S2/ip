package duke.task;

/**
 * This class inherits from the Task class and represents a todo task.
 */
public class Todo extends Task {
    /**
     * Initializes the todo task.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
