package duke.data.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public abstract String storageStr();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusValue() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    public String outputMarked() {
        isDone = true;
        return "Well done. I've marked this tasked as done:\n";
    }

    public String outputUnmarked() {
        isDone = false;
        return "Noted. I've marked this tasked as undone:\n";
    }

    public void mark() {
        isDone = true;
    }

    public boolean contains(String string) {
        return this.description.contains(string);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
