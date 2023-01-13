public class Task {
    private String taskDescription;
    private TaskStatus taskStatus;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = TaskStatus.NOT_DONE;
    }

    public Task mark() {
        this.taskStatus = TaskStatus.DONE;
        return this;
    }

    public Task unmark() {
        this.taskStatus = TaskStatus.NOT_DONE;
        return this;
    }

    @Override
    public String toString() {
        return this.taskStatus + " " + this.taskDescription;
    }
}
