public class Task {
    private String taskName;
    private boolean isDone;
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
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
        return this.taskName;
    }
}
