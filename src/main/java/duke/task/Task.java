package duke.task;

/**
 * Task
 */
public class Task {
    private String title;
    private boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String toCsv() {
        return String.format("%s,%s", (isDone ? 1 : 0), title);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), title);
    }
}
