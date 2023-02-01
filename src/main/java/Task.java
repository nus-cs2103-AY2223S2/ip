public class Task {
    protected String name;
    protected boolean isDone;
    protected String taskType;

    public Task(String name, String taskType) {
        this.name = name;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String name, String taskType, boolean isDone) {
        this.name = name;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getName() {
        return this.name;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String description() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.name);
    }

    public String formattedDescription() {
        String status = isDone ? "1" : "0";
        String description = String.format("%s | %s | %s", this.taskType,
                status, this.name);
        return description;
    }
}
