package dude.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static int count;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public static void addTaskCount() {
        count++;
    }

    public static void removeTaskCount() {
        count--;
    }

    public abstract String toRaw();
}
