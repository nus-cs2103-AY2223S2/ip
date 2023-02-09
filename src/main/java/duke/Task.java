package duke;

/**
 * Represents a task to be done.
 * Is abstract since tasks can be either deadlines, todos or events.
 */
public abstract class Task {
    private final String description;
    private boolean isCompleted;

    /**
     * Constructor for a task.
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }
    /**
     * Constructor for a task.
     * @param description Description of the task
     * @param isCompleted Whether the task is completed
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Getter for the description of the task
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for whether the task is completed
     * @return Completion status of the task
     */
    public boolean getCompletion() {
        return this.isCompleted;
    }
    public void setCompletion() {
        this.isCompleted = !this.isCompleted;
    }
    @Override
    public String toString() {
        String icon = this.isCompleted ? "[X] " : "[ ] ";
        return icon + this.description;
    }
}