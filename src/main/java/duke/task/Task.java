package duke.task;

/**
 * General duke.task.
 */
public class Task {

    protected String task;
    protected boolean completed;

    /**
     * Constructor to create new duke.task
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    /**
     * Mark the duke.task as complete
     */
    public void markComplete() {
        this.completed = true;
    }

    /**
     * Unmark the duke.task to be incomplete
     */
    public void unmarkComplete() {
        this.completed = false;
    }

    /**
     * Placeholder for toData function for Task.
     * @return Empty String
     */
    public String toData() {
        return "";
    }

    @Override
    public String toString() {
        String statusIcon = completed ? "X" : " ";
        return "[" + statusIcon + "] " + this.task;
    }
}
