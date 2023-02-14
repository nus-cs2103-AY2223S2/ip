package uwuke.model;

public abstract class Task {
    private boolean isMarkedDone = false;
    private final String taskName; // Do not allow task name to change for now

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.taskName);
    }

    public String markDone() {
        this.isMarkedDone = true;
        String msg = String.format("Nice! I've marked this task as done:\n  %s", this.toString());
        return msg;
    }

    public String unmarkDone() {
        this.isMarkedDone = false;
        String msg = String.format("Ok, I've marked this task as not done yet:\n  %s", this.toString());
        return msg;
    }

    public boolean isDone() {
        return isMarkedDone;
    }

    private String getStatusIcon() {
        char c = isMarkedDone ? 'X' : ' ';
        return String.format("[%c]", c);
    }
}
