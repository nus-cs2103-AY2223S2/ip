package duke.task;

import java.time.LocalDateTime;

public class Deadline extends TimedTask {
    /**
     * Represents Deadline Task for the Duke program
     */

    /**
     * Empty constructor for a Deadline instance.
     */
    public Deadline() {
        super();
    }

    /**
     * Constructor for a Deadline instance.
     *
     * @param status Indicate if task is done or not
     * @param des    Description of task. Includes deadline of task
     */
    public Deadline(boolean status, String des, LocalDateTime end) {
        super();
        setMark(status);
        setDes(des);
        setEndFromApp(end);
    }

    /**
     * Method to print out status of task onto the console.
     */
    @Override
    public String printStatus() {
        String s = (isMark) ? "X" : " ";
        return "[D][" + s + "] " + getDes() + " (by: " + toStringConsoleEnd() + ")\n";
    }

    /**
     * Methods to print out status of task onto file.
     *
     * @return
     */
    @Override
    public String toString() {
        String s = (isMark) ? "X" : " ";
        return this.taskNumber + " | D | " + s + " | " + getDes() + " | " + super.toStringFileEnd();
    }
}
