public class Task {
    protected String description;
    protected boolean isDone;
    protected String from;
    protected String to;
    protected String by;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.from = "";
        this.to = "";
        this.by = "";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getType() {
        return " ";
    }

    public String getTiming() {
        return "(from: " + from + " to: " + to + ")";
    }

    public String toString() {
        return "";
    }
}
