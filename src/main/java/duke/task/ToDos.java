package duke.task;

/**
 * Represents a Todos Task.
 * @author pzhengze
 */
public class ToDos extends Task {
    /**
     * Constructor for a Todos object.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the task has been done.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the Todos Task in String form.
     * This shows if the Todos Task has been done, represented by [X].
     * @return The Todos Task in String form.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the Task in String form specific for saving.
     * @return The Task in String form
     */
    @Override
    public String save() {
        return String.format("todo %s-%s\n", this.description, this.isDone);
    }
}
