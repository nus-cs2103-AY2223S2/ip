abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    private String getMark() {
        if (this.isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String toString() {
        return getMark() + " " + this.taskName;
    }
}
