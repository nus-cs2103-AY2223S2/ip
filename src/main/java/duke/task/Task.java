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

    public Task(String type, String doneStatus, String description) {
        this.doneStatus = doneStatus;
        this.type = type;
        this.description = description;
        isTaskDone(this.doneStatus);
    }
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
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Checks if current status of the task is completed.
     *
     * @param doneStatus - the status of the task if it is done.
     *
     * @return boolean - Returns true or false based on the task.
     */
    public boolean isTaskDone(String doneStatus) {
        if (doneStatus.equals(" ")) {
            this.isDone = false;
        } else this.isDone = true;
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
