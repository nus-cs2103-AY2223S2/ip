package task;

/**
 * Base class for all tasks consisting of task details and status.
 */
public class Task {
    /** Count total Tasks created */
    //public static Integer count = 0;

    /** Name of task */
    protected String taskName;

    /** Task status */
    protected Boolean isTaskDone;

    /**
     * Creates an instance of Task.
     *
     * @param taskName Name of task.
     */
    protected Task(String taskName) {
        this.taskName = taskName;
        this.isTaskDone = false;
        //Task.count++;
    }

    /**
     * Mark this task as done.
     */
    public Task markDone() {
        this.isTaskDone = true;
        return this;
    }

    /**
     * Mark this task as not done.
     */
    public Task markUnDone() {
        this.isTaskDone = false;
        return this;
    }

    /**
     * Use for overriding for child classes.
     */
    public String writeToFile() {
        return "";
    }

    @Override
    public String toString() {
        if (!isTaskDone) {
            return "[ ] " + this.taskName;
        }
        return "[X] " + this.taskName;
    }

    public String getDetails() {
        return this.taskName;
    }

    public String getTaskDetails() {
        return this.toString();
    }
}
