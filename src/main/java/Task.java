public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
        System.out.println("This task has been marked as completed:\n" + this + "\n");
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("This task has been marked as uncompleted:\n" + this + "\n");
    }

    @Override
    public String toString() {
        String statusicon = this.getStatusIcon();
        String str = "";
        str = String.format("[" + statusicon + "] " + this.description);
        return str;
    }
}

