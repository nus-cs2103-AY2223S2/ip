package genie.task;

/**
 * Deals with creating tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor that takes in the task descriptor and initialises task as undone.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status of the task (marked done or undone).
     * @return "X" if marked done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the status of the task to marked done.
     */
    public void markDone() {
        this.isDone = true;
    }
    /**
     * Changes the status of the task to marked undone.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the status and description of the task.
     * @return formatted <code>String</code>
     */
    @Override
    public String toString() {
        return this.getStatusBox() + this.description;
    }

    /**
     * Retrieves the status of the task.
     * @return formatted <code>String</code>
     */
    public String getStatusBox() {
        return "[" +  this.getStatusIcon() + "] ";
    }

    /**
     * Retrieves the status and description of the task formatted for .txt file
     * @return formatted <code>String</code>
     */
    public String toFileFormat() {
        return this.getStatusBox() + this.description;
    }
}

