public class Task {

    private String task;
    private boolean completed;

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

    @Override
    public String toString() {
        String statusIcon = completed ? "X" : " ";
        return "[" + statusIcon + "] " + this.task;
    }
}
