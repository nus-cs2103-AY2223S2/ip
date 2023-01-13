public class Task {
    private String taskDescription;
    private int taskIndex;
    private Status taskStatus;

    public Task(String taskDescription, int taskIndex) {
        this.taskDescription = taskDescription;
        this.taskIndex = taskIndex;
        this.taskStatus = Status.NOT_DONE;
    }

    public Task mark() {
        this.taskStatus = Status.DONE;
        return this;
    }

    public Task unmark() {
        this.taskStatus = Status.NOT_DONE;
        return this;
    }

    @Override
    public String toString() {
        return this.taskIndex + "." + this.taskStatus + " " + this.taskDescription;
    }
}
