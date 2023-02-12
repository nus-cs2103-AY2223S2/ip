package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String eventDetail;

    protected LocalDate time;

    /**
     * Initialises the object
     *
     * @param detail a string description of the entire description of the task
     * @param time   deadline date
     */
    public Deadline(String detail, LocalDate time) {
        super(detail);
        this.eventDetail = detail.trim();
        this.time = time;
    }

    /**
     * Outputs task details
     *
     * @return a string including the task name and time
     */
    @Override
    public String toString() {

        return "[D]" + "[" + this.getStatusIcon() + "] "
            + eventDetail + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";

    }

    /**
     * Gets the type of task
     *
     * @return a string "deadline"
     */
    public String getType() {
        return "D";
    }

    /**
     * Gets the type of task
     *
     * @return a String representing event detail
     */
    public String getDetail() {
        return this.eventDetail;
    }

    /**
     * Gets the time of task
     *
     * @return a String representing time
     */
    public String getTime() {
        return time.toString();
    }

}
