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

    public Deadline(String activity, String bydate, String bytime) {
        super(activity);
        this.byDate = LocalDate.parse(bydate);
        this.byTime = LocalTime.parse(bytime, DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                this.byTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}

