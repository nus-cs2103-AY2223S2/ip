package duke.tasks;

/**
 * ToDo is a task with just a description.
 */
public class ToDo extends Task {
    /**
     * A constructor for ToDo.
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates the string representation of todo task.
     *
     * @return The string representation of todo task.
     */
    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }

    /**
     * Converts Todo task into a string representation to be saved into storage.
     *
     * @return String representation of todo task.
     */
    public String saveFormat() {
        return String.format("T | %s", super.saveFormat());
    }

}
