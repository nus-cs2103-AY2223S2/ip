package duke.tasks;

/**
 * Represents Task class
 */
public class Task {
    private static int taskNum;
    private String description;
    private boolean isDone;


    /**
     * Task constructor
     * @param description task name
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        taskNum++;
    }

    /**
     * if isDone is true, gets status icon "X", otherwise " "
     * @return "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "   ");
    }

    /**
     * Mark as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * gets description
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Override toString method
     * @return String
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }

    /**
     * Override file method, changing into data saving format
     * @return String
     */
    public String file() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + getDescription();
    }
}
