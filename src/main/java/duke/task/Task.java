package duke.task;

public abstract class Task {

    protected boolean isDone;
    protected String name;

    public Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    protected abstract String getTaskType();

    public String serialize() {
        String serialized = String.format("%s|%s|%s", this.getTaskType(), this.isDone ? 1 : 0, this.name);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("[%s][%s] %s", this.getTaskType(), this.getStatusIcon(), this.name);
        return s;
    }
}
