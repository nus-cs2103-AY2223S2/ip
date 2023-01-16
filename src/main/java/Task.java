public class Task {
    private final String taskName;
    private boolean completed;

    public Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.completed = false;
    }

    public void changeCompletion() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        String icon = this.completed ? "[X] " : "[ ] ";
        return icon + this.taskName;
    }
}