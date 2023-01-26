public class Task {
    protected String description;
    protected boolean isDone;
    protected char type = ' ';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieve mark status of task
     * 
     * @return Return a string "X" if the task is mark as completed, otherwise return blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not done
     */
    public void unmark() {
        this.isDone = false;
    }
}
