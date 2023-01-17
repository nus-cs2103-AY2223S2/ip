public class Task {
    private String taskName;
    private int taskId;
    private static int numberOfTasks = 0;
    public Task(String taskName) {
        this.taskName = taskName;
        this.taskId = numberOfTasks + 1;
        numberOfTasks++;
    }
    @Override
    public String toString() {
        return this.taskId + ". " + this.taskName;
    }
}
