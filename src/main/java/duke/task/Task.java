package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }

    public String getName() {
        return "You should not be getting this output here";
    }

    public String getDescription() {
        return this.description;
    }

    public void markStatus(boolean status) {
        this.isDone = status;
    }

    public void markStatus(String s) {
        this.isDone = !s.equals("0");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
