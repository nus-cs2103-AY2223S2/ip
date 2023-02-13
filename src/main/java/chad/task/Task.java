package chad.task;

/**
 * General duke.task.
 */
public abstract class Task {

    protected String task;
    protected boolean isCompleted;

    /**
     * Constructor to create new task
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    /**
     * Mark the task as complete
     */
    public void markComplete() {
        this.isCompleted = true;
    }

    /**
     * Unmark the task to be incomplete
     */
    public void unmarkComplete() {
        this.isCompleted = false;
    }

    public String getDescription() {
        return this.task;
    }

    @Override
    public String toString() {
        String statusIcon = isCompleted ? "X" : " ";
        return "[" + statusIcon + "] " + this.task;
    }

    /**
     * Abstract method for toData function for Task.
     * @return Empty String
     */
    public abstract String toData();

}
