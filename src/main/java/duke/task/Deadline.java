package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Class of Deadline which creates the task with date.
 */
public class Deadline extends Task {

    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Constructor for Deadline.
     *
     * @param activity the activity of the task written by the user.
     * @param byDate the start datetime of the task.
     * @param byTime the end datetime of the task.
     */
    public Deadline(String activity, String byDate, String byTime) {
        super(activity);
        this.byDate = LocalDate.parse(byDate);
        this.byTime = LocalTime.parse(byTime, DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        String res = "[D]" + super.toString() + " (by: "
                + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + this.byTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        return res;
    }
}

