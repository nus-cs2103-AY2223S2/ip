package duke.task;

public class Task {
    /**
     * Represent a general Task.
     */
    boolean isMark;
    String des;
    int taskNumber;

    /**
     * Constructor to create a generic Task.
     *
     * @param status
     * @param des
     */
    public Task(boolean status, String des) {
        this.isMark = status;
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

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public void setTaskNumber(int i) {
        this.taskNumber = i;
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
    public boolean getMark() {
        return isMark;
    }

    /**
     * Method to define the status of the Task
     *
     * @param mark boolean. True for done, false for not done
     */
    public void setMark(boolean mark) {
        this.isMark = mark;
    }

    public String printStatus() {
        String s = (isMark) ? "X" : " ";
        return "[" + s + "] " + this.des + "\n";
    }

    /**
     * Method to return string representation of task
     *
     * @return String representation of task
     */
    public String toString() {
        String s = (isMark) ? "X" : " ";
        return this.taskNumber + " |   | " + s + this.des;
    }
}
