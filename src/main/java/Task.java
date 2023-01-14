/**
 * Task encapsulates the description of a task and its 'done' status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get icon representing the 'done' status of this Task.
     * @return the 'done' status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}