package duke;

public class ToDos extends Task {
    /**
     * Represenation of todo tasks.
     */

    /**
     * Empty constructor for an instance of a todo task
     */
    public ToDos() {
        super();
    }

    /**
     * Constructor for an instance of a todo task
     *
     * @param status
     * @param des
     */
    public ToDos(boolean status, String des) {
        super(status, des);
    }

    /**
     * Method to display status of todo task
     */
    @Override
    public void printStatus() {
        String s = (status) ? "X" : " ";
        System.out.println("[T][" + s + "] " + this.des);
    }

    /**
     * Method to return string representation of status of todo task
     *
     * @return String representation of status of todo task
     */
    @Override
    public String toString() {
        String s = (status) ? "X" : " ";
        return "T | " + s + " | " + getDes();
    }
}
