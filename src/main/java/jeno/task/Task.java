package jeno.task;

/**
 * Parent Class for Task
 */
public class Task {
    private boolean isMarked;
    private String taskName;

    /**
     * Constructor for Task
     * @param name of task
     */
    public Task(String name) {
        this.isMarked = false;
        this.taskName = name;
    }

    /**
     * Constructor for empty Task
     */
    public Task() {
        this.isMarked = false;
        this.taskName = "Blank Task";
    }

    /**
     * Marks a task as complete
     */
    public void markTask() {
        this.isMarked = true;
    }

    /**
     * Unmarks a task to incomplete
     */
    public void unmarkTask() {
        this.isMarked = false;
    }

    /**
     * Get a name of a Task
     * @return Name of task
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Converts task to its task log format to be saved in task log file
     * @return String representing task in task log format
     */
    public String toLog() {
        if (this.isMarked) {
            return (" | 1 | " + this.taskName);
        } else {
            return (" | 0 | " + this.taskName);
        }
    }

    /**
     * Converts task to string format which is echoed to user
     * @return String representation of task
     */
    @Override
    public String toString() {
        if (this.isMarked) {
            return ("[X] " + this.taskName);
        } else {
            return ("[ ] " + this.taskName);
        }
    }
}
