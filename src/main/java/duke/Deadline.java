package duke;

import java.time.LocalDateTime;

/**
 * Contains the Deadline Task object with variable by
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Public constructor
     *
     * @param value = the name of the task
     * @param by = the dateTime of the task to complete by
     * @param mark = the status of the task, whether it is completed
     */
    public Deadline(String value, LocalDateTime by, boolean mark) {
        super(value,mark);
        this.by = by;
    }

    /**
     * @return = returns a string that represents the deadline object
     *         that is stored in a file
     */
    public String toFile() {
        return "deadline," + super.isMark() + "," + super.getValue() + "," + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }


}
