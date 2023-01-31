package duke.task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; //assumed not done when initiated
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toTXT() {
        String isDoneAsInt = isDone ? "1" : "0";
        return String.format("%s | %s", isDoneAsInt, this.description);
    }

    /**
     * Helper function to check if a task description contains a keyword
     * @param keyword derived from input by user
     * @return true if keyword is found in description
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
