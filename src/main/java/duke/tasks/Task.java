package duke.tasks;

/**
 * Represents a task within Duke.
 */
public abstract class Task {
    private boolean isDone;
    private String title;

    public Task(String title, boolean isDone) {
        this.isDone = isDone;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns formatted string representation of the Task, for storage in memory.
     *
     * @return String representation of the Task, for storage in memory.
     */
    public abstract String convertToMemoryString();
}
