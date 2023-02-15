package hachi.tasks;

/**
 * Encapsulates a job to be done.
 */
public class Task {
    protected String input;
    private boolean isDone;

    /**
     * Task constructor.
     *
     * @param input The description of the task.
     */
    public Task(String input) {
        this.input = input;
        this.isDone = false;
    }

    public String getInput() {
        return this.input;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Return the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] " + this.input);
        } else {
            return String.format("[ ] " + this.input);
        }
    }

}
