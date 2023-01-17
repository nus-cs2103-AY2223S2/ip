// Class for each individual tasks.

public class Task {

    private String content;
    private boolean isDone = false;
    public Task(String content) {
        this.content = content;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getTypeIcon() {
        return " ";
    }

    @Override
    public String toString() {
        return content;
    }

    // Status and message
    public String fullMessage() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), this);
    }


}
