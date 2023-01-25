package duke.packages;

public abstract class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }
    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean getCompletion() {
        return this.completed;
    }
    public void setCompletion() {
        this.completed = !this.completed;
    }
    @Override
    public String toString() {
        String icon = this.completed ? "[X] " : "[ ] ";
        return icon + this.description;
    }
}