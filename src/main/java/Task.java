public abstract class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void changeCompletion() {
        this.completed = !this.completed;
    }
}