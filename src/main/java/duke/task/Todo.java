package duke.task;

/**
 * The Todo class extends the Task class to represent a Todo task.
 *
 * @author owen-yap
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description);
        assert !description.isEmpty();
    }
    /**
     * Constructs the Todo task with the given description and completion status.
     *
     * @param description the description of the Todo task
     * @param isDone the completion status of the Todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Overrides the `toString` method to return the Todo task in the form "[T][taskStatus] taskDescription".
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
