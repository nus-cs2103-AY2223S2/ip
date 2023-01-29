package hachi.tasks;

/**
 * Encapsulates a job to be done.
 */
public class Task {
    private String input;
    private boolean status;

    /**
     * Task constructor.
     *
     * @param input The description of the task.
     */
    public Task(String input) {
        this.input = input;
        this.status = false;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.status = true;
    }

    /**
     * Marks a task as not done.
     */
    public void unmark() {
        this.status = false;
    }

    /**
     * Return the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (this.status) {
            return String.format("[X] " + this.input);
        } else {
            return String.format("[ ] " + this.input);
        }
    }

}
