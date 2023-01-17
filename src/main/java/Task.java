
public class Task {
    protected String description;
    protected boolean isDone;
    private static int numOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static int getNumOfTask() {
        return numOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(Boolean b) {
        isDone = b;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

