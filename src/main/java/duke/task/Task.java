package duke.task;

public class Task {
    /**
     * Represent a general Task.
     */
    boolean status;
    String des;

    /**
     * Constructor to create a generic Task.
     *
     * @param status
     * @param des
     */
    public Task(boolean status, String des) {
        this.status = status;
        this.des = des;
    }

    /**
     * Empty constructor to create a generic Task
     */
    public Task() {

    }

    /**
     * Method to configure information read from file, into Task objects for TaskList
     *
     * @param s Line extracted from save file
     */
    public void configure(String[] s) {
        setDes(s[0]);
    }

    /**
     * Method to return description of Task.
     *
     * @return String representation of description of Task
     */
    public String getDes() {
        return this.des;
    }

    /**
     * Method to define the description of the Task
     *
     * @param des Description of task
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * Method to return status of Task
     *
     * @return Boolean to represent status of task. True for done, false for not done.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Method to define the status of the Task
     *
     * @param status boolean. True for done, false for not done
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String printStatus() {
        String s = (status) ? "X" : " ";
        return "[" + s + "] " + this.des + "\n";
    }

    /**
     * Method to return string representation of task
     *
     * @return String representation of task
     */
    public String toString() {
        String s = (status) ? "X" : " ";
        return "  | " + s + this.des;
    }
}
