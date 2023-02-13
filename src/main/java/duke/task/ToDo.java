package duke.task;

public class ToDo extends Task {
    /**
     * Representation of todo tasks.
     */

    /**
     * Empty constructor for an instance of a todo task
     */
    public ToDo() {
        super();
    }

    /**
     * Constructor for an instance of a todo task
     *
     * @param status
     * @param des
     */
    public ToDo(boolean status, String des) {
        super(status, des);
    }

    /**
     * Method to display status of todo task
     */
    @Override
    public String printStatus() {
        String s = (isMark) ? "X" : " ";
        return "[T][" + s + "] " + this.des + "\n";
    }

    /**
     * Method to return string representation of status of todo task
     *
     * @return String representation of status of todo task
     */
    @Override
    public String toString() {
        String s = (isMark) ? "X" : " ";
        return this.taskNumber + " | T | " + s + " | " + getDes();
    }
}
