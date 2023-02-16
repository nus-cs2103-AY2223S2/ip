//Reference from the given code provided on CS2103 module website
package duke.task;


/**
 * Class of Task which contains the details of the task.
 */
public class Task {
    private String description;
    private String doneStatus;
    private String type;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param type        the type of command of the given task.
     * @param doneStatus  checks if the task is mark or unmark.
     * @param description the description of the task written by the user.
     */
    public Task(String type, String doneStatus, String description) {
        this.doneStatus = doneStatus;
        this.type = type;
        this.description = description;
        isTaskDone(this.doneStatus);
    }

    /**
     * Constructor for Task.
     *
     * @param description the description of the task written by the user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the current status of the task.
     *
     * @return String - Returns the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks if current status of the task is completed.
     *
     * @param doneStatus - the status of the task if it is done.
     * @return boolean - Returns true or false based on the task.
     */
    public boolean isTaskDone(String doneStatus) {
        if (doneStatus.equals(" ")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        return this.isDone;
    }

    /**
     * Retrieves the description of the given task.
     *
     * @return String - the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void setMark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void setUnmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String res = "";
        if (this.type == null) {
            res = "[" + this.getStatusIcon() + "]" + " " + this.description;
        } else {
            res = "[" + this.type + "]" + "[" + this.getStatusIcon() + "]" + " " + this.description;
        }
        return res;
    }
}
