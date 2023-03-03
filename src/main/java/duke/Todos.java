package duke;

/**
 * Extends the Task class, representing a task that is a type of Todo.
 *  @author Muhammad Reyaaz
 *  @version %I% %G%
 *  @see Task
 *  @since 11
 */
class Todos extends Task {

    /**
     * Constructs a Todos object with a given description.
     * @param description the description of the task
     */
    Todos(String description) {
        super(description);
    }

    /**
     * Constructs a Todos object with a given description and a status indicating whether the task is done or not.
     * @param description the description of the task
     * @param isDone a boolean value indicating whether the task is done or not
     */
    Todos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks a new Todos object with the status marked as done.
     * @return a new Todos object with the status marked as done
     */
    @Override
    Todos markAsDone() {
        return new Todos(getDescription(), true);
    }

    /**
     * Marks a new Todos object with the status marked as undone.
     * @return a new Todos object with the status marked as undone
     */
    Todos markAsUndone() {
        return new Todos(getDescription(), false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
