package duke;

public class Deadlines extends TimedTask {
    /**
     * Represents Deadline Task for the Duke program
     */

    /**
     * Empty constructor for a Deadline instance.
     */
    public Deadlines() {
        super();
    }

    /**
     * Constructor for a Deadline instance.
     *
     * @param status Indicate if task is done or not
     * @param des    Description of task. Includes deadline of task
     */
    public Deadlines(boolean status, String des, String end) {
        super();
        setStatus(status);
        setDes(des);
        setEnd(end);
    }

    /**
     * Method to print out status of task onto the console.
     */
    @Override
    public String printStatus() {
        String s = (status) ? "X" : " ";
        return "[D][" + s + "] " + getDes() + " (by: " + toStringConsoleEnd() + ")\n";
    }

    /**
     * Methods to print out status of task onto file.
     *
     * @return
     */
    @Override
    public String toString() {
        String s = (status) ? "X" : " ";
        return "D | " + s + " | " + getDes() + " | " + super.toStringFileEnd();
    }
}
