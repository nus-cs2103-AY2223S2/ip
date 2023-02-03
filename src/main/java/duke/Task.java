package duke;

/**
 * Base class for all tasks consisting of task details and status.
 */
public class Task {
    /** Count total Tasks created */
    public static Integer count = 0;

    /** Name of task */
    protected String taskName;

    /** Task status */
    protected Boolean taskDone;

    /**
     * Creates an instance of Task.
     *
     * @param taskName Name of task.
     */
    protected Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
        Task.count++;
    }

    /**
     * Mark this task as done.
     */
    public void markDone() {
        this.taskDone = true;
    }

    /**
     * Mark this task as not done.
     */
    public void markUnDone() {
        this.taskDone = false;
    }

    /**
     * Use for overriding for child classes.
     */
    public String writeToFile() {
        return "";
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[ ] " + this.taskName;
        }
        return "[X] " + this.taskName;
    }
}
