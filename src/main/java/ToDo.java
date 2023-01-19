/**
 * Represents a Todo task
 */
public class ToDo extends Task {

    /**
     * Creates a toDo task object
     * @param description Describes the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of todo task
     * @return String representation of todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
