import java.util.ArrayList;

public abstract class Task {
    private String name;
    private boolean isDone;
    public Task (String name, boolean isDone) {
        this.name = name.strip();
        this.isDone = isDone;
    }

    public abstract String getType();
    public abstract String getStatus();
    public abstract String getDescription();

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    public String parse() {
        return getType() + "|" + getStatus()+ "|" + getDescription();
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }
}
