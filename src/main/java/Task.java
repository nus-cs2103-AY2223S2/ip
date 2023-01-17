public abstract class Task {
    private boolean completed;
    private String description;

    Task (String description) {
        this.description = description;
        this.completed = false;
    }

    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
