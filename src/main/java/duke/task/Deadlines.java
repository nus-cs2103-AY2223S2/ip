package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that creates a deadline object for the deadline command
 */
public class Deadlines extends Task {

    protected LocalDateTime date;

    /**
     * A constructor to create a deadline object
     *
     * @param description What task to be kept track
     * @param date        date and time the task is due
     */
    public Deadlines(String description, String date) throws DukeException {
        super(description);
        if (date.length() <= 0) {
            throw new DukeException("No Date!");
        }
        assert (date.length() > 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.date = LocalDateTime.parse(date, formatter);

    }


    /**
     * To String method that returns the specified (MMM d YYYY HHmm)
     *
     * @return a string of the above format
     */
    @Override
    public String toString() {

        return "[D]" + super.toString()
                + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
