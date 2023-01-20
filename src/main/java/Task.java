public class Task {
    protected String description;
    protected boolean isDone;

    public static int counter = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter += 1;
    }

    public static void decreaseCounter() {
        counter -= 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
