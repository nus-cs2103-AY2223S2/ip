package duke.task;

/**
 * Handles task object to encapsulate commonalities in subclasses.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task object.
     *
     * @param description Description of task.
     * @param isMarked Boolean to check if task is marked.
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isDone = isMarked;
    }

    /**
     * Returns status if marked or not via a form of string
     *
     * @return Marked icon as String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns status if marked or not.
     *
     * @return isDone as boolean.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets status if marked or not.
     *
     * @param status Status of mark task.
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }

    @Override
    public int compareTo(Task otherTask) {
        return this.toString().compareTo(otherTask.toString());
    }

    /**
     * Return string of task.
     *
     * @return Formatted task with details as String.
     */
    @Override
    public String toString() {
        return String.format("[" + getStatusIcon() + "] " + this.description);
    }
}
