public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     * @param description Title of the task
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns a string that indicates if the task is done or not.
     * @return A string representation of status of this task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
