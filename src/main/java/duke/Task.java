package duke;

// custom duke.Task class to store individual tasks that the user enters
public abstract class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public Task(Boolean isMarked, String Task_content) {
        this.description = Task_content;
        this.isMarked = isMarked;
    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]"); // mark done task with X
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public String getDescription() {
        return description;
    }

    public String addDivider() {
        String d = " | ";
        int marked = this.isMarked() ? 1 : 0;
        return marked + d + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public boolean isMarked() {
        return this.isMarked;
    }
}

