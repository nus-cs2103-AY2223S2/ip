package task;

/**
 * Represents a Todo
 */
public class Todo extends Task {

    /**
     * Constructor to initialize a todo object
     *
     * @param desc The title of the todo
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns the string representation of the todo
     *
     * @return The string representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
