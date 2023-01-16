package Storage;

public class Task {
    protected String task;
    protected boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return "[" + status() + "] " + this.task;
    }

    public String status() {
        return (done ? "X" : " ");
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
