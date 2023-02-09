package duke.task;

/**
 * Class for Todo object.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo object.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns string representation of the task and its status.
     *
     * @return String representation of the task and the marked status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
