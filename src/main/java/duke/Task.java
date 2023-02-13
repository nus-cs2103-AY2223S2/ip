package duke;

/**
 * Task class
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for instantiating a Task object
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task
     * @return String completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X": " ");
    }

    /**
     * Returns the description of the task
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a Task
     * @return String string representation of a task, which includes completion status and description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
