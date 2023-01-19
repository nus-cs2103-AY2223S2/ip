/**
 * Represents a generic task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor to be inherited
     * @param description Describes the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the task description
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon
     * @return "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmarkFromDone() {
        this.isDone = false;
    }

    /**
     * String representation of a task
     * @return String representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
