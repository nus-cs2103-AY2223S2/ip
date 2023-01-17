package task;

public class Task {
    private boolean markedDone = false;
    private final String taskName; // Do not allow task name to change for now

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.taskName);
    }

    public String markDone() {
        this.markedDone = true;
        String msg = String.format("Nice! I've marked this task as done:\n  %s", this.toString());
        return msg;
    }

    public String unmarkDone() {
        this.markedDone = false;
        String msg = String.format("Ok, I've marked this task as not done yet:\n  %s", this.toString());
        return msg;
    }

    private String getStatusIcon() {
        char c = markedDone ? 'X' : ' ';
        return String.format("[%c]", c);
    }
}
