package duke;

/**
 * A class that represents a todo task
 */
public class ToDo extends Task {
    /**
     * Initializes an Todo object with the given values.
     *
     * @param name The name of the todo
     * @return A todo instance
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Initializes an Todo object with the given values.
     *
     * @param name The name of the todo
     * @param isDone The status of the todo
     * @return A todo instance
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of the Todo task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
