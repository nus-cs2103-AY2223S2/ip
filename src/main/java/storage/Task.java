package storage;

/**
 * Represents a single task
 * Consists of a description, and it's status (if it's done or not)
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the specified description
     * @param description The specified description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task
     * @return Status icon as a string
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Customised string representation of a task
     * @return Task as custom string
     */
    @Override
    public String toString() {
        String status = this.getStatusIcon();
        return String.format("[%s] %s", status, this.description);
    }

    /**
     * Converts each task into a String in the data format to be saved
     * @return String formatted into data to be saved
     */
    public String toData() {
        int status = this.isDone ? 1 : 0;
        return String.format("%d | %s", status, this.description);
    }
}
