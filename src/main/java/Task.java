public class Task {
    private boolean markedDone = false;
    private final String taskName; // Do not allow task name to change for now

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markDone() {
        this.markedDone = true;
    }

    public void unmarkDone() {
        this.markedDone = false;
    }

    public String getStatusIcon() {
        char c = markedDone ? 'X' : ' ';
        return String.format("[%c]", c);
    }
}
