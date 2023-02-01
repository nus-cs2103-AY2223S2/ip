package duke.task;

/**
 * Task class that represents a task
 */
public class Task {
    private String description;
    private boolean isDone;
    private String type;

    /**
     * Constructor for Task class
     *
     * @param description desc of the task
     * @param isDone whether a task is done
     * @param type type of the task
     */
    public Task(String description, boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the string representation of the task's current status
     *
     * @return string representation of the task's status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set whether the task is done
     *
     * @param isDone represents whether a task is done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * String representation of a task object
     *
     * @return string representation of a task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.description);
    }
}
