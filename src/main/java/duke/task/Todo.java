package duke.task;

/**
 * Represents a todo object that inherits from Task.
 */
public class Todo extends Task {
    /**
     * Constructs a todo object from task description.
     * @param description The description of the todo object.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo() {
        super();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
