public class Task {
    private final String description;
    private boolean isDone = false;

    public Task (String description) {
        this.description = description;
    }

    public Task createTask(String description) {
        return new Task(description);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone? "[X] " : "[ ] ") + description;
    }


}
