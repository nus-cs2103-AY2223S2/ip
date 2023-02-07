package duke.task;

/**
 * Parent Task class which contains details of a task
 */
public class Task {
    public String taskString;
    public boolean isCompleted;

    /**
     * Constructor of Task
     * @param taskString user taskString input
     */
    public Task(String taskString) {
        this.taskString = taskString;
        this.isCompleted = false;
    }

    /**
     * Check current status of task, whether it is marked completed or not
     * @return X means completed and space means not completed
     */
    public String currentTaskStatus() {
        if(this.isCompleted) {
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
    public String getTask() {
        return this.taskString;
    }

    public String toString() {
        return this.currentTaskStatus() + " " + this.taskString;
    }
}
