public abstract class Task {
    private TaskStatus status;

    public Task() {
        status = TaskStatus.Pending;
    }

    public boolean isCompleted() {
        return status == TaskStatus.Completed;
    }

    public void markCompleted() {
        status = TaskStatus.Completed;
    }

    abstract String description();
}