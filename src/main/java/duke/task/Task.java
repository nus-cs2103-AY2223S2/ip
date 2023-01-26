package duke.task;

public class Task {
    private String description;
    private boolean isDone;
    private String type;

    public Task(String description, boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.description);
    }
}
