package duke.task;

/**
 * Represents a general task, parent class for Todo, Deadline and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     * @param description Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints the status of completion of a task.
     * @return [X] if the task is marked done, [] if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " + this.description : "[] " + this.description); // mark done task with X
    }

    /**
     * Sets the task's status as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task's status as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Save the task in desired format.
     * @return Task saved in text file in format " |task name".
     */
    public String toRecord() {
        return (isDone ? "1|" : "0|") + description;
    }
}
