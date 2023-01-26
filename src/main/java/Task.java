public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this(description, false);
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    void completeTask() {
        this.isDone = true;
    }

    void undoTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", isDone ? "X" : " ", description);
    }
}
