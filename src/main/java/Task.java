import java.util.ArrayList;

public class Task {
    protected String name;
    protected boolean isDone;
    public Task (String name) {
        this.name = name.strip();
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String getName() {
        return this.name;
    }
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getName();
    }
}
