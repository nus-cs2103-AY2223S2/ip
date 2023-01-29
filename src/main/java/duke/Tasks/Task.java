package duke.Tasks;

public class Task {
    /** integer identifier in the TaskList*/
    protected int id;
    /** Description of the task*/
    protected String desc;
    /** if task is completed*/
    protected boolean isDone;

    public Task(int id, String description) {
        this.id = id;
        this.desc = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setIsDone(boolean status) {
        this.isDone = status;
    }
    public void setId(int id) {
        this.id = id;
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
        return id + ". [" + statusIcon + "] " + this.desc;
    }
}
