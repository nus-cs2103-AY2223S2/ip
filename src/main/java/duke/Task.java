package duke;

/**
 * An abstract class that groups all categories of tasks that duke
 * is able to keep track of. All tasks have a description and also
 * a boolean value that indicates if the task is done already.
 */
public abstract class Task {
    private boolean done;
    private String task;

    public Task(String name, boolean done) {
        this.done = done;
        this.task = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getTask() {
        return this.task;
    }

    /**
     * Method to help keep track of tasks in duke.txt for future
     * references.
     *
     * @return String that represents the task to do.
     */
    public abstract String summary();

    @Override
    public String toString() {
        String checkmark = this.done ? "✓" : " ";
        return String.format("[ %s ] %s", checkmark, this.task);
    }
}
