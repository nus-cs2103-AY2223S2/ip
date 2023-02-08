package windycall.task;

/**
 * Serves as an abstraction of different types of tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String getTaskTypeIcon();


    /**
     * Returns fileFormat of a specific task that will be stored
     * inside data file with consistency
     *
     * @return a String representation of fileFormat of a task
     */
    public abstract String getFileFormat();

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean matchFilterWord(String filterWord) {
        return description.toLowerCase().indexOf(filterWord.toLowerCase()) != -1;
    }


    public String getCurrentDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}