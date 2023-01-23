public abstract class Task {
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
        return "Nice! I've marked this task as done:" + "\n" + "[" + this.getTaskType() + "][X] " + this.toString();
    }

    public String markAsUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:" + "\n" + "[" + this.getTaskType() + "][ ] "
                + this.toString();
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return this.name;
    }
}
