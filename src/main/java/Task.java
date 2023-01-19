public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        /* Retrieves the Status of the task
        *  [X] for marked as completed
        *  [ ] for not yet completed */
        return (isDone ? "[X]" : "[ ]");
    }

    public void mark() {
        this.isDone = true;
    } /* Marks the task as completed */

    public void unmark() {
        this.isDone = false;
    }  /* Removes the completed marks from the task */

    @Override
    public String toString() {
        /* Returns the status icon and the description of the task */
        return getStatusIcon() + " " + description;
    }
}
