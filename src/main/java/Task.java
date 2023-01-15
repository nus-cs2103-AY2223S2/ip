public class Task {
    private final String description;
    
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUnDone() {
        isDone = false;
    }

    private boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }
}