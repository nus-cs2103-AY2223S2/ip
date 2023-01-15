public class Task {
    boolean isDone;
    String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    boolean isDone() {
        return isDone;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

