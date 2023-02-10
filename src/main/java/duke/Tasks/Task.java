package duke.tasks;

/**
 * Represents Task class
 */
public class Task {
    public static int taskNum;
    private String description;
    public boolean isDone;


    /**
     * Task constructor
     * @param description task name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskNum++;
    }

    /**
     * if isDone is true, gets status icon "X", otherwise " "
     * @return "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + getDescription();
    }
}
