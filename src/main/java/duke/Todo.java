package duke;

/**
 * A todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with given description.
     *
     * @param description Description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
