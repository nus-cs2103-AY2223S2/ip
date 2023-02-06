package duke.task;

/**
 * Abstract class for task
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task object. Task is assumed to be "not done" when initiated.
     * @param description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    /**
     * Converts Task to a form that can be saved to .txt files and then read again by Duke
     * @return String in the correct format
     */
    public String toTxt() {
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
