public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() { this.isDone = false; }

    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    public String sendOutputToFile() {
        return String.format("Task | %d | %s", isDone ? 1 : 0, description);
    }

}
