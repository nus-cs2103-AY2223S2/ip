package james.task;

/**
 * The task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Creates a new task.
     * @param description The task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean setStatus(boolean isDone) {
        this.isDone = isDone;
        return isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toStoreString() {
        String isDoneIndicator = isDone ? "1" : "0";
        return isDoneIndicator + " | " + this.description;
    }

}


