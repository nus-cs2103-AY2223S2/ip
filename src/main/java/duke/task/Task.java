package duke.task;

/**
 * Task class that represents a task.
 */

public class Task {
    private String description;
    private boolean isMark;

    public Task(String description) {
        this.description = description;
        this.isMark = false;
    }

    public String getDescription() {
        return description;
    }

    public Character getMark() {
        return isMark ? 'X' : ' ';
    }

    /**
     * Toggles the mark of the task.
     */
    public void toggleMark() {
        this.isMark = !this.isMark;
    }

    
    /** Returns the details of the task in a string format.
     * @return String The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", getMark(), this.description);
    }
}
