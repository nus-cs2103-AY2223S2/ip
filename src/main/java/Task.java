public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
}
