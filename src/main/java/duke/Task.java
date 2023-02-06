package duke;

/**
 * Task is the base class for todos, deadlines and events.
 * It contains a description and an indicator of whether the task is done.
 */
public class Task {

    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns a "X" if task is done and " " if not
     *
     * @return String of done or not icon
     */
    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a "T" for todo, "D" for deadline, "E" for event
     *
     * @return String icon
     */
    public String getIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * Sets a task to be done or not done
     *
     * @param val boolean value of whether task is done
     */
    public void setDone(boolean val) {
        done = val;
    }

    public String getDescription() {
        return this.description;
    }

}
