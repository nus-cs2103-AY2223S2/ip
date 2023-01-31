package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

/**
 * Creates a duke.task.Deadline class that inherits from duke.task.Task
 * to handle deadline tasks
 */
public class Deadline extends Task {
    protected final LocalDateTime deadline;
    /**
     * @param name: a string indicating the name of the task
     * @param status: a String indicating whether the task is done or not
     * @param deadline: a string indicating the deadline of the task
     */
    public Deadline(String name, int status, String dlString) {
        super(name, status);
        this.deadline = Parser.formatDateTime(dlString);
    }

    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }
    /**
     * overrides the toString method
     */
    public String toString() {
        return "[D]" + status + " " + name + "(by " + Parser.TransformDateTime(deadline) + ")";
    }
    
    public String toStoreFormatString() {
        return String.format("D/%s/%d/%s", super.name, this.getStatusNo(), Parser.reverseFormatDateTime(deadline));
    }
}