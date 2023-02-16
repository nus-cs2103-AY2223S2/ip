package duke.task;

/**
 * Parent Task class which contains details of a task
 */
public abstract class Task {
    private String taskString;
    private boolean isCompleted;

    /**
     * Constructor of Task
     * @param taskString user taskString input
     */
    public Task(String taskString) {
        this.taskString = taskString;
        this.isCompleted = false;
    }

    public abstract String getTask();

    public abstract String fullDetails();

    /**
     * Check current status of task, whether it is marked completed or not
     * @return X means completed and space means not completed
     */
    public String currentTaskStatus() {
        if (this.isCompleted) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Mark a task is completed
     */
    public void markTask() {
        this.isCompleted = true;
    }

    /**
     * Mark a task as not completed
     */
    public void unmarkTask() {
        this.isCompleted = false;
    }


    public String getTaskType() {
        return this.toString().substring(1, 2);
    }

    /**
     * Get description of task
     * @return String of task description
     */

    public String toString() {
        return this.currentTaskStatus() + " " + this.taskString;
    }

    /**
     * Check if the two task has exact same detail
     * Details such as task description, deadline, event start date and event end date
     * @param t Task to check with
     * @return True if the two tasks has exact same details, which means this task is duplicated
     */
    public boolean equals(Task t) {
        if (this == t) {
            return true;
        }
        return this.fullDetails().equals(t.fullDetails());
    };

    public String toBeSaved() {
        return this.currentTaskStatus() + "///" + this.taskString;
    }
}
