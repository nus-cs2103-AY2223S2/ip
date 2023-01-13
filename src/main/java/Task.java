public class Task {
    private String taskDescription;
    private int taskIndex;

    public Task(String taskDescription, int taskIndex) {
        this.taskDescription = taskDescription;
        this.taskIndex = taskIndex;
    }

    @Override
    public String toString() {
        return taskIndex + ". " + taskDescription;
    }
}
