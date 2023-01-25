package duke.tasktypes;

/**
 * Abstract representation of a Task object.
 */
public abstract class Task {

    /** Description of a task */
    protected String description;
    /** Indicated whether a task is completed */
    protected boolean isDone;

    /**
     * Constructor to initialize a Task.
     * To be called by the concrete Child classes of Task.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark a task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as incomplete.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a Task.
     * Appends the completed status of the task to the front.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return status + " " + this.description;
    }

    /**
     * Returns a String representation of Task that is compatible with Saving and Loading.
     *
     * @return String representation of Task in Save Format.
     */
    public abstract String getSaveFormat();
}

