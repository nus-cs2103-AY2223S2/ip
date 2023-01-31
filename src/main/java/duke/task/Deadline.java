package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

/**
 * A Deadline task comes with a deadline
 */
public class Deadline extends Task {
    protected final LocalDateTime deadline;

    /**
     * @param name: a string indicating the name of the task
     * @param status: an int indicating whether the task is done or not
     * @param deadline: a string indicating the deadline of the task
     */
    public Deadline(String name, int status, String deadline) {
        super(name, status);
        this.deadline = Parser.formatDateTime(deadline);
    }

    /**
     * private method that helps to store the status of the task as binary numbers
     * @return 0 is the task is not done, 1 if the task is done.
     */
    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * overrides the toString method: displays the type, then status, then name, then deadline
     */
    public String toString() {
        return "[D]" + status + " " + name + "(by " + Parser.TransformDateTime(deadline) + ")";
    }

    /**
     * method that helps with updating local tasks
     * @return a String to be written to local hard disk
     */
    public String toStoreFormatString() {
        return String.format("D/%s/%d/%s", super.name, this.getStatusNo(),
                Parser.reverseFormatDateTime(deadline));
    }
}
