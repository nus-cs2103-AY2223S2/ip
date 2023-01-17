public class Task {
    protected String description;
    protected boolean isDone;

    protected static int counter = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markStatus(boolean status) {
        this.isDone = status;
    }

    //...
}
