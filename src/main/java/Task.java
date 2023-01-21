import java.io.Serializable;

/**
 * This Task class represents a task.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 100;
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task with a name.
     *
     * @param name The name of a task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * @return True when marked done.
     */
    public boolean setAsDone() {
        return this.isDone = true;
    }

    /**
     * Unmarks the task, making the task not done.
     *
     * @return True when successfully unmarked.
     */
    public boolean setAsNotDone() {
        return !(this.isDone = false);
    }

    /**
     * Gets the done status of the task.
     *
     * @return True if done and false if not done.
     */
    public boolean isMarkedDone() {
        return this.isDone;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.name : "[ ] " + this.name;
    }

}
