package tasks;

/**
 * todo class
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo Task object
     * @return A string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
