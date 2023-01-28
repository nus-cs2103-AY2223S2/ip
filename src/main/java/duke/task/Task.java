package duke.task;

public abstract class Task {
    private boolean isCompleted; //by default the task should not be completed
    private final String task;

    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public abstract String getTaskType();

    public abstract String getStatus();

    public abstract String getDescription();

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markUncompleted() {
        isCompleted = false;
    }

    public void markCompleted() {
        isCompleted = true;
    }

    public String getTask() {
        return this.task;
    }

    public String encode() {
        return getTaskType() + " | " + getStatus() + " | " + getDescription();
    }

    public boolean containsKeyword(String keyword) {
        return this.task.contains(keyword);
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " + task : "[ ] " + task);
    }

}
