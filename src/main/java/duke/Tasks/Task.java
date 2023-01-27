package duke.Tasks;

public class Task {
    protected String description;
    public boolean isDone;

    public static int taskNum;
    public boolean isExit;

    /**
     * Task constructor
     * @param description
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
        taskNum++;
        this.isExit = false;
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
        String status = isDone? "1" : "0";
        return " | " + status + " | " + getDescription();
    }
}
