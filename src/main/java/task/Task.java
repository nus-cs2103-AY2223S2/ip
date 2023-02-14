package task;

/**
 * The superclass of all kinds of tasks.
 */
public class Task implements Comparable<Task> {
    protected String name;
    protected boolean isMarked;

    /**
     * Constructor
     *
     * @param name Content of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Mark the task.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmark the task.
     */
    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    @Override
    public int compareTo(Task other) {
        if (this instanceof Deadline && other instanceof Deadline) {
            Deadline thisDeadline = (Deadline) this;
            Deadline otherDeadline = (Deadline) other;
            return thisDeadline.getDeadline().compareTo(otherDeadline.getDeadline());
        }
        return this.toString().compareTo(other.toString());
    }
}
