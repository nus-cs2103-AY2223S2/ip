//Reference from the partial solution provided on CS2103 module website
package duke.task;

/**
 * A task class which contains description, whether it is completed, type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeofTask = "";
    protected String doneString;

    /**
     * Constructor for the task class with description,
     * type of task and done indicator.
     *
     * @param description String representing the task and its instructions.
     * @param typeOfTask  Type of task indicated.
     * @param doneStr     Tasks is marked complete.
     */
    public Task(String description, String typeOfTask, String doneStr) {
        this.description = description;
        this.doneString = doneStr;
        this.typeofTask = typeOfTask;
        isTaskDone();


    }

    /**
     * Constructor for task class with description only.
     *
     * @param description String representing the task and its instructions.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the string input or the task is done according to its string.
     *
     * @return boolean - Returns true if contains "X" else will return false.
     */
    public boolean isTaskDone() {
        if (this.doneString.equals("X")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
        return this.isDone;
    }

    /**
     * Gets the status of the task.
     *
     * @return String - Returns "X" if the isDone variable is true, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed with the variable.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete with the variable.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Methods which outputs the task object to a string.
     *
     * @return String Description with type of task and done indicator and task description.
     */
    public String toString() {
        if (typeofTask.equals("")) {
            return "[" + this.getStatusIcon() + "]" + " " + this.description;
        } else {
            return "[" + typeofTask + "]" + "[" + this.getStatusIcon() + "]" + " " + this.description;
        }
    }

}
