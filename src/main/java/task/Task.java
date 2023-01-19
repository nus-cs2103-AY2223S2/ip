package task;

/**
 * Represents a task
 */
public abstract class Task {
    protected String desc;
    protected boolean isMarked;

    /**
     * Constructor to initialize a task object
     *
     * @param desc The title of the task
     */
    public Task(String desc) {
        this.desc = desc;
        this.isMarked = false;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks the task
     */
    public void unMark() {
        isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Returns the string representation of the task
     *
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }

}
