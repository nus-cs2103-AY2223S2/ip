public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws Exception{
        if (isDone) {
            throw new Exception("Already Done");
        }
        isDone = true;
    }

    public void unmarkDone() throws Exception {
        if (!isDone) {
            throw new Exception("Task is already marked undone");
        }
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.description;
    }
}