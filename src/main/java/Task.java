public abstract class Task {
    private TaskStatus status;
    private int id;

    public Task(int id) {
        status = TaskStatus.Pending;
        this.id = id;
    }

    public boolean isCompleted() {
        return status == TaskStatus.Completed;
    }

    public void markCompleted() {
        status = TaskStatus.Completed;
    }

    public void markPending() {
        status = TaskStatus.Pending;
    }

    public int id() {
        return this.id;
    }

    abstract String description();
}