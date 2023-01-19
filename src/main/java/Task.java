public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String markAsDone() {
        if (isDone) {
            return String.format("Perhaps you forgot, but this task was already marked done!:\n\t%s", this);
        }
        isDone = true;
        return String.format("Well done! I've marked this task as done:\n\t%s", this);
    }

    public String markNotDone() {
        if (!isDone) {
            return String.format("No need to tell me, the task was not even marked as done!:\n\t%s", this);
        }
        isDone = false;
        return String.format("Okay, I have marked this task as not done:\n\t%s", this);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}
