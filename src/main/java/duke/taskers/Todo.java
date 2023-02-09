package duke.taskers;

/**
 * The Todo class when a Task is a todo.
 */
public class Todo extends duke.taskers.Task {

    public static final String TASK_TYPE = "todo";


    /**
     * Constructor of todo.
     *
     * @param description Description of what the todo task is.
     * @param isDone True if todo is done, false if todo is not done.
     */
    public Todo(String description, boolean isDone, boolean isPriority) {
        super(description, isDone, isPriority);
    }

    /**
     * Formats the string before being added to the duke storage file.
     *
     * @return The formatted string to be added to the duke storage file.
     */

    public String formatStringForFile() {
        return String.format("TODO / %s",
                super.helpFormatString());
    }

    /**
     * String representation of the todo.
     *
     * @return Todo string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
