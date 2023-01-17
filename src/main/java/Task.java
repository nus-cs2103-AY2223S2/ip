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

    public String markTask() {
        isDone = true;
        return "Nice! I've marked this task as done: \n [X] "  + description;
    }

    public String unmarkTask() {
        isDone = false;
        return "OK, I've marked this task as not done yet: \n [ ] " + description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
