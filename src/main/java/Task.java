public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return getStatus() + " " + taskName;
    }
}
