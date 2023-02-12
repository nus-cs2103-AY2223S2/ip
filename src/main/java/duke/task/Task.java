package duke.task;

/**
 * General duke.task.
 */
public abstract class Task {

    protected String task;
    protected boolean completed;

    /**
     * Constructor to create new task
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    /**
     * Mark the task as complete
     */
    public void markComplete() {
        this.completed = true;
    }

    /**
     * Unmark the task to be incomplete
     */
    public void unmarkComplete() {
        this.completed = false;
    }

    public String getDescription() {
        return this.task;
    }

    @Override
    public String toString() {
        String statusIcon = completed ? "X" : " ";
        return "[" + statusIcon + "] " + this.task;
    }

    /**
     * Abstract method for toData function for Task.
     * @return Empty String
     */
    public abstract String toData();

}
