public abstract class Task {
    protected String description;
    protected TaskType taskType;
    protected boolean isDone;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}