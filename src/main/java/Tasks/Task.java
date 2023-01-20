package Tasks;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markNotDone() {
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskStatus() {
        return done ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }
}
