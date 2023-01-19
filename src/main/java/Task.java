public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:" + "\n" + "[X] " + this.name;
    }

    public String markAsUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:" + "\n" + "[ ] " + this.name;
    }
}
