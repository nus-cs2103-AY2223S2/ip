package duke.task;

/**
 * Represent tasks
 */
public class Task {
    protected String type;
    protected String detail;
    protected boolean marked;

    /**
     * Intializes a new Task object
     * @param type of task
     * @param detail of task
     */
    public Task(String type, String detail) {
        this.type = type;
        this.detail = detail;
        this.marked = false;
    }

    /**
     * Initialize a Task object with given value
     *
     * @param type   type of task
     * @param detail content of task
     * @param marked
     */
    public Task(String type, String detail, boolean marked) {
        this.type = type;
        this.detail = detail;
        this.marked = marked;
    }


    /**
     * Marks task as done.
     *
     * @mark task as done.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Unarks task as undone.
     *
     * @unmark task as undone.
     */
    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[" + this.type + "][X] " + this.detail;
        } else {
            return "[" + this.type + "][ ] " + this.detail;
        }
    }
}
