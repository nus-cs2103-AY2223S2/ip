package duke.task;

public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String mark() {
        if (!isDone) {
            this.isDone = true;
            String toShow = "Nice! I've marked this task as done!\n" +
                    String.format("[%s] %s", this.getStatusIcon(), this.taskName);
            return toShow;
        } else {
            String toShow = "The task is already done!";
            return toShow;
        }
    }

    public String unmark() {
        if (isDone) {
            this.isDone = false;
            String toShow = "I've marked this task as undone, you lazy bum\n" +
                    String.format("[%s] %s", this.getStatusIcon(), this.taskName);
            return toShow;
        } else {
            String toShow = "The task is already done!";
            return toShow;
        }
    }

    public abstract String[] encode();



    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.taskName);
    }
}
