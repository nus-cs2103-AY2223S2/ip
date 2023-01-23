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

    public void markDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + this);
    }

    public void markNotDone() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + this);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
