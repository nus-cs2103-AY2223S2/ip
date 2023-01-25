public class Tasks {
    protected String description;
    protected boolean isDone;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String log() {
        if (this.isDone) {
            return " | 1 | " + this.description;
        } else {
            return(" | 0 | " + this.description);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override 
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}