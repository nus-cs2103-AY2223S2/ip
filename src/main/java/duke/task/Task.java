package duke.task;

/**
 * Class to store duke.task description along with completion status.
 */
public abstract class Task {

    protected String description;
    protected String tag;
    protected boolean isDone;

    /**
     * Constructor to create a Task.
     *
     * @param description String containing Deadline description.
     */
    public Task(String description, String tag) {
        this.description = description;
        this.tag = tag;
        this.isDone = false;
    }

    /**
     * Returns string indicating task type.
     *
     * @return Task type.
     */
    public abstract String getType();

    /**
     * Sets task status to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task status to not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Sets task tag.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }


    /**
     * Returns task tag.
     *
     * @return String representing tag.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Returns task status icon.
     * If task is done, returns a space (" ").
     * If task is not done, returns an "X".
     *
     * @return String indicating status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns task description.
     *
     * @return String indicating task description.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description + (this.tag == null ? "" : " (#" + this.tag + ")");
    }
}
