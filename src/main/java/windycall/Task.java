package windycall;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String getTaskTypeIcon();

    public abstract String fileFormat();

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    public String getCurrentDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}