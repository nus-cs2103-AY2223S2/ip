public class Task {
    private String taskName;
    private boolean isDone;
    private int taskNumber;
    public Task(String taskName, int taskNumber) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskNumber = taskNumber;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.isDone ? "[X]" : "[ ]", this.taskName);
    }
}
