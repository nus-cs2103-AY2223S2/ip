package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for Deadline object.
 *
 * @author Pearl Twe
 * @version CS2103T AY22/23 Semester 2
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Constructor for deadline task
     * @param description
     * @param date
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Encode task into String for easier decode when tasks from loading duke.txt
     * @return String format of task
     */
    @Override
    public String encode() {
        return "deadline"
                + " " + this.isDone
                + " " + this.getPriority()
                + " " + this.description
                + " " + "/by: "
                + " " + this.date;
    }

    /**
     * Convert task into String for display in taskList
     * @return String of task type, status, task description, and date to complete task by
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
