package duke.task;

public abstract class Task {
    private boolean isCompleted;
    private String description;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public String toSaveFormat() {
        String status = isCompleted ? "1" : "0";
        return status + "||" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
