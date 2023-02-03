package duke;

/**
 * Base class for all tasks consisting of task details and status.
 */
public class Task {
    /** Count total Tasks created */
    public static Integer count = 0;

    protected String taskName;
    protected  Boolean taskDone;

    protected Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
        Task.count++;
    }

    public void markDone() {
        this.taskDone = true;
    }

    public void markUnDone() {
        this.taskDone = false;
    }

    //to override
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
