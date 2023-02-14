package duke.tasks;

/**
 * A task with no deadline.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task.
     * @param keyword The keyword command to create a new ToDo task.
     * @param message The description of the task.
     * @param completed The completion status of the task.
     */
    public ToDo(String keyword, String message, Boolean completed) {
        super(keyword, message, completed);
    }

    @Override
    public String provideDetails() {
        return this.isComplete? "[T]" + "[x] " + this.taskDescription
                : "[T]" + "[ ] " + this.taskDescription;
    }
}
