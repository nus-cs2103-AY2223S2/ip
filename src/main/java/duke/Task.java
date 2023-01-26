package duke;

public abstract class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean getCompletion() {
        return this.isCompleted;
    }
    public void setCompletion() {
        this.isCompleted = !this.isCompleted;
    }
    @Override
    public String toString() {
        String icon = this.isCompleted ? "[X] " : "[ ] ";
        return icon + this.description;
    }
}