public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String mark() {
        this.isDone = true;
        return String.format("\t Nice! I've marked this task as done:\n\t   %s", this);
    }

    public String unMark() {
        this.isDone = false;
        return String.format("\t Ok, I've marked this task as not done yet:\n\t   %s", this);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public abstract void writeToString(String filePath) throws DukeWriteException;
}
