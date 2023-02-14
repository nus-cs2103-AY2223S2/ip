package windycall.task;

/**
 * Serves as an abstraction of different types of tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String tag = "";

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
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

    public void changeTag(String tag) {
        this.tag = tag;
    }


    public String getCurrentDescription() {
        return "[" + this.getStatusIcon() + "]" + (tag.isEmpty() ? " " : "[#" + tag + "] ")
                + this.description;
    }
}
