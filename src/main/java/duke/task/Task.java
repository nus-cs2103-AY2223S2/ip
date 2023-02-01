package duke.task;

/**
 * Abstract class for task representation.
 */
public abstract class Task {
    private String description;
    private TaskType taskType;
    private boolean isDone;

    /**
     * Creates a task.
     *
     * @param description The description of the task.
     * @param taskType The type of task.
     */
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

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
