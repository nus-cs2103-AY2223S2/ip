public class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String mark() {
        this.isDone = true;
        return String.format("\tNice! I've marked this task as done:\n\t  [%s] %s", this.getStatusIcon(), this.description);
    }

    public String unmark() {
        this.isDone = false;
        return String.format("\tOK, I've marked this task as not done yet:\n\t  [%s] %s", this.getStatusIcon(), this.description);
    }


    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
