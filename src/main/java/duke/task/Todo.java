package duke.task;

/**
 * A subclass of Task that represents
 * a todo task.
 *
 * @author  Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Todo extends Task {

    /**
     * Constructor of the todo class.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a todo task.
     * @return String representation.
     */
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
