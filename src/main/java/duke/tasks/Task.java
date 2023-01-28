package duke.tasks;

/**
 * Represents a task within Duke.
 */
public abstract class Task {
    private boolean done;
    private String title;

    public Task(String title, boolean done) {
        this.done = done;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns formatted string representation of the Task, for storage in memory.
     *
     * @return String representation of the Task, for storage in memory.
     */
    public abstract String convertToMemoryString();
}
