package jeo.task;

/**
 * Represents a Todo task.
 * @author Goh Jun How
 * @version 0.3
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task object with the specified description.
     * @param description String describing the task.
     */
    public ToDo(String description, String tags) {
        super(description, tags);
    }

    /**
     * Gets the string representation of the todo task.
     * @return String representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
