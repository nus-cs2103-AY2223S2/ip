package duke.task;

/**
 * Represents an action that is to be done. A <code>Task</code> object corresponds to
 * the action that is to be done by the user e.g., <code>"cook dinner"</code>
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String remarks;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.remarks = "";
    }

    public Task(String description, String remarks) {
        this(description);
        this.remarks = remarks;
    }

    /**
     * Marks the task as done.
     *
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     *
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description.trim();
    }

    /**
     * Returns status icon of the task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the remarks of the task.
     *
     * @return Remarks.
     */
    public String getRemarks() {
        return remarks.trim();
    }

    public boolean equals(Task task) {
        return (task.getDescription().equals(this.getDescription()) && task.getRemarks().equals(this.getRemarks()));
    }
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}