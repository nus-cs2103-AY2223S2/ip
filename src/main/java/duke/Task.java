package duke;

/**
 * Encapsulates a task object.
 *
 * @author Sean Chin Jun Kai
 */
public class Task {
    protected String description;

    protected boolean isDone;

    /**
     * Constructor for creating a Task object.
     *
     * @param description name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of the status of the task.
     *
     * @return status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     *
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a task.
     *
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string representation of a Task object which users can see in the command line.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);

    }

    /**
     * Returns string representation of a task object to store in txt file.
     *
     * @return String representation of task.
     */
    public String getText() {
        return String.format("| %s | %s", this.getStatusIcon() == "X" ? "1" : "0", this.description);
    }
}
