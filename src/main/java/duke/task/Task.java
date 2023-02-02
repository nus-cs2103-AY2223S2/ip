package duke.task;

public abstract class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toSaveFormat() {
        String status = completed ? "1" : "0";
        return status + "||" + description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
