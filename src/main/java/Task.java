public class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void changeCompletion() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        String icon = this.completed ? "[X] " : "[ ] ";
        return icon + this.description;
    }
}