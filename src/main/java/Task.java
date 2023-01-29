abstract public class Task {
    protected String title;
    protected boolean isDone;

    public Task (String title) {
        this.title = title;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    abstract String save();

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return status + this.title;
    }
}
