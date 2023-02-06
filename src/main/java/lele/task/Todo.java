package lele.task;

/**
 * A task to be completed without a set date.
 */
public class Todo extends Task {

    /**
     * Instantiates the parent instance.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Identifier of the type of task.
     *
     * @return The type of task in a string.
     */
    @Override
    public String getName() {
        return "T";
    }

    /**
     * Prepends a todo type of
     * its parent's string result.
     *
     * @return The string result of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
