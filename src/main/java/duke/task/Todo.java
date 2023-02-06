package duke.task;

/**
 * Todo is a type of Task.
 * All objects of class Todo have a 'description' field.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task with the given description.
     * @param description The given description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The string representation of the Todo object.
     * @return The name of the Todo Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
