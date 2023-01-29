
/**
 * Contains information of a task
 * Contains description and completion status of the task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object
     * Completion status is set to false
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task object
     * Completion status is set to false
     *
     * @param description Description of the task
     * @param isDone Completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes status of task back to not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns task from save string
     *
     * @param description Description of the task
     * @param status Completion status of the task
     * @return Task in save string format
     */
    public static Task load(String description, String status) {
        boolean isDone = status.equals("X");
        return new Task(description, isDone);
    }

    /**
     * Returns task in save string format
     *
     * @param divider Divider used to separate fields
     * @return Task in save string format
     */
    public String toSave(String divider) {
        return " "
                + divider + getStatusIcon()
                + divider + description;
    }

    /**
     * Returns completion status and description of task
     *
     * @return Completion status and description of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}
