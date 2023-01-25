package duke.tasktypes;

/**
 * Abstract representation of a Task object.
 */
public abstract class Task {

    /** Description of a task */
    protected String description;
    /** Indicated whether a task is completed */
    protected boolean done;

    /**
     * Constructor to initialize a Task.
     * To be called by the concrete Child classes of Task.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Mark a task as completed.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Mark a task as incomplete.
     */
    public void markUndone() {
        this.done = false;
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
        if (this.done) {
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

