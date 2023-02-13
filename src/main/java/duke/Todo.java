package duke;

/**
 * Class for To-do object, which encapsulates the description of the task.
 *
 * @author Eric Leow Yu Quan
 */
public class Todo extends Task {

    /**
     * Constructor for a Task instance.
     *
     * @param description a simple description of this Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a To-do instance with a [T] indicator.
     *
     * @return the desired string representation of a To-do instance.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of a To-do instance with a [T] indicator.
     *
     * @return the desired string representation of a To-do instance.
     */
    public String parse() {
        return "[T]" + super.parse() + super.addOn();
    }
}
