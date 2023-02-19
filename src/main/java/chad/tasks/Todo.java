package chad.tasks;

/**
 * Represents a Todo task, encapsulating only the description of the Todo.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo task.
     * @param description The name of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of a Todo task, in the format <code>[T] [doneIcon] description</code>.
     * @return A string representation of this Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

