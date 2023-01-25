public abstract class Task {
    protected boolean isDone = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean b) {
        this.name = name;
        this.isDone = b;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    public abstract String asCSV();
}
