public class Task {
    protected String name;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
