package duke.tasks;

/**
 * Class that models all Tasks
 */
public class Task {
    /** Description of the task*/
    protected String desc;
    /** if task is completed*/
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description task
     */
    public Task(String description) {
        this.desc = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setIsDone(boolean status) {
        this.isDone = status;
    }
    public String getDesc() {
        return this.desc;
    }
    /**
     * Process Task to String to store in duke.txt
     * @return Processed String
     */
    public String toFile() {
        return "T|" + this.desc;
    }
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.desc;
    }
}
