//Reference from the partial solution provided on CS2103 module website
package duke.task;

/**
 * A task class which contains description, whether it is completed, type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeofTask = "";
    protected String doneStr;

    /**
     * @param description
     * @param typeOfTask
     * @param doneStr
     */
    public Task(String description, String typeOfTask, String doneStr) {
        this.description = description;
        this.doneStr = doneStr;
        checkisdonestr();
        this.typeofTask = typeOfTask;

    }

    /**
     * @param description
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
    public boolean checkisdonestr() {
        if (this.doneStr.equals("X")) {
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
       // System.out.println("Nice! I've marked this task as done:\n");
    }

    /**
     * Marks the task as incomplete with the variable.
     */
    public void markAsUndone() {
        this.isDone = false;
       // System.out.println("OK, I've marked this task as not done yet:\n");
    }

    /**
     * @return String
     */
    public String toString() {
        if (typeofTask.equals("")) {
            return "[" + this.getStatusIcon() + "]" + " " + this.description;
        } else {
            return "[" + typeofTask + "]" + "[" + this.getStatusIcon() + "]" + " " + this.description;
        }
    }

}
