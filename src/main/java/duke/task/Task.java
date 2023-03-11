package duke.task;

/**
 * Represents a task that the user has.
 */
public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    /**
     * Marks this task as done if it is currently marked as undone.
     *
     * @return True if this task was not completed, false otherwise.
     */
    public boolean markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    /**
     * Marks this task as uncompleted if it is currently marked as done.
     *
     * @return True if this task was marked as done, false otherwise.
     */
    public boolean markAsUndone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String mark;
        if (this.isDone) {
            mark = "[X]";
        } else {
            mark = "[ ]";
        }
        return mark + " " + this.name;
    }
}
