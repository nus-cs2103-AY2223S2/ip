public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        if (isDone) {
            throw new IllegalStateException("Task is already marked as done");
        }
        isDone = true;
    }

    public void markAsNotDone() {
        if (!isDone) {
            throw new IllegalStateException("Task is already marked as not done");
        }
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
