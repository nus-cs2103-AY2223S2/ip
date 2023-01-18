public class Task {
    String taskName;
    boolean isDone;

    public Task(String name) {
        this.taskName = name;
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.taskName;
    }
}
