package duke.task;

/**
 * Todo, default and most generic of a Task item
 */
public class Todo extends Task {
    /**
     * Creates a Todo
     *
     * @param title of the Task that that is being created
     */
    public Todo(String title) {
        this(title, false);
    }

    /**
     * Creates a Todo, with isDone field. Mostly used by Storage
     *
     * @param title  of the Task that that is being created
     * @param isDone status of the Task
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Get a String representation to display to user of a Task
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
