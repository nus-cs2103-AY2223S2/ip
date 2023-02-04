package duke.logic.task;

/**
 * Task parent class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task object with boolean.
     *
     * @param description Description of task.
     * @param isDone Whether task is marked complete.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;

    }

    /**
     * Gives the type of the Task object.
     *
     * @return String of the type of Task.
     */
    public String getType() {
        return "task";
    }

    /**
     * Gives the status icon based on whether task is complete.
     *
     * @return String [X] if complete or [ ] if not.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Marks task as done by setting isDone boolean to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done by setting isDone boolean to false.
     */
    public void unmarkDone() {
        this.isDone = false;
    }
}
