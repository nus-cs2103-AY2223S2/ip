package duke.task;

/**
 * Represents a Task.
 */
public abstract class Task {

    /** Description of this Task */
    protected String desc;
    /** Status of this Task */
    protected boolean isMarked;

    /**
     * A constructor to initialize a Task.
     *
     * @param desc The title of this Task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isMarked = false;
    }

    /**
     * Marks this Task as complete.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks this Task.
     */
    public void unMark() {
        isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }

}
