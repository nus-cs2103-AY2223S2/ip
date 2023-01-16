public class Task {
    protected final String value;
    protected boolean isDone;

    public Task(String value) {
        this.value = value;
        this.isDone = false;
    }

    public String getValue() {
        return this.value;
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
        return String.format("[%s] %s", getStatusIcon(), value);
    }
}
