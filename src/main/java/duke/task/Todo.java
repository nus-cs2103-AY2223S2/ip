package duke.task;

/**
 * Todo task class.
 */
public class Todo extends Task {


    /**
     * Constructor for Todo object.
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);

    }

    /**
     * Returns string representation of Todo task.
     * @return String of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
