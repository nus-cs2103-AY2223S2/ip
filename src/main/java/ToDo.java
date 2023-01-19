/**
 * A class that represents a todo task
 */
public class ToDo extends Task {
    /**
     * Initialize an Todo object with the given values.
     *
     * @param name The name of the todo
     * @return A todo instance
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the Todo task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
