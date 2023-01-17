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

    @Override
    public String toString() {
        return content;
    }


}
