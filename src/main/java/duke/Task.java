package duke;

/**
 * Represents a task to be done.
 * Is abstract since tasks can be either deadlines, todos or events.
 */
public abstract class Task {
    private final String description;
    private boolean completed;

    /**
     * Constructor for a task.
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }
    /**
     * Constructor for a task.
     * @param description Description of the task
     * @param completed Whether the task is completed
     */
    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
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
        return this.completed;
    }
    public void setCompletion() {
        this.completed = !this.completed;
    }
    @Override
    public String toString() {
        String icon = this.completed ? "[X] " : "[ ] ";
        return icon + this.description;
    }
}