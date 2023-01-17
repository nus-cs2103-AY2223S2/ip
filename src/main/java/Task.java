public class Task {
    protected String description;
    protected boolean isDone;

    static int noOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.noOfTasks += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public void remove() {
        Task.noOfTasks -= 1;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
