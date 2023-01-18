public class Task {
    protected String desc;
    protected boolean isDone;
    protected String taskType;

    public Task(String desc) {
        this.desc = desc;
        this.taskType = null;
        this.isDone = false;
    }
    public Task(String desc, String taskType) {
        this.desc = desc;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isDone() {
        return isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return String.format("[%s][%s] %s", taskType, getStatusIcon(), desc);
    }
}