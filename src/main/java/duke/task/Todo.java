package duke.task;

/**
 * Handles Todo object.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo object.
     *
     * @param description Description of Deadline.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo object.
     *
     * @param description Description of Todo.
     * @param marked Boolean of Todo status.
     */
    public Todo(String description, boolean marked) {
        super(description, marked);
    }

    /**
     * Return string of Todo.
     *
     * @return Formatted Todo with details as String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
