package duke.task;

public class Task {
    private String description;
    private String doneStatus;
    private String type;
    private boolean isDone;

    public Task(String type, String doneStatus, String description) {
        this.doneStatus = doneStatus;
        this.type = type;
        this.description = description;
        isTaskDone(this.doneStatus);
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public boolean isTaskDone(String doneStatus) {
        if (doneStatus.equals(" ")) {
            this.isDone = false;
        } else this.isDone = true;
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
