package lele.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The task to be completed with a set end date.
 * Date provided must abide by M/dd/yyyy format (e.g 2/12/2019),
 * which will then be converted to MMM d yyyy (e.g Feb 12 2019).
 */
public class Deadline extends Task {
    protected String unFormatted;
    protected String byDate;
    protected String byTime;

    /**
     * Instantiates the description, date and time provided.
     *
     * @param description Description of Deadline.
     * @param byDate The due date.
     * @param byTime The due time.
     */
    public Deadline(String description, String byDate, String byTime) {
        super(description);
        this.unFormatted = byDate;
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        LocalDate d = LocalDate.parse(byDate, parseFormatter);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        this.byDate = d.format(formatter);
        this.byTime = byTime;
    }

    /**
     * Identifier of the type of task.
     *
     * @return The type of task in a string.
     */
    @Override
    public String getName() {
        return "D";
    }

    /**
     * The date and time of the deadline, to be stored in the user's data.
     *
     * @return A string representation of the date and time of the deadline.
     */
    public String getDateTime() {
        return this.unFormatted + " " + this.byTime;
    }

    /**
     * Prepends the task type and appends the due date nad time of the task.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: " + this.byDate + " " + this.byTime + ")";
    }
}
