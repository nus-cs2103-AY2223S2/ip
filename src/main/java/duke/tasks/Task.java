package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public boolean emptyTask() {
        if (description.equals("")) {
            return true;
        } else return false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String saveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getDescription());
    }
}
