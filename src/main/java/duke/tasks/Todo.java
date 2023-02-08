package duke.tasks;

/**
 * Represents user todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     *  Returns string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
