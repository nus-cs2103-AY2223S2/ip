package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Deadline date */
    protected LocalDate byDate = null;
    /** Deadline time */
    protected LocalTime byTime = null;

    public Deadline(String description, String by) {
        super(description);
        // eg. 2019-12-01 10:15
        String[] strs = by.split(" ");
        this.byDate = LocalDate.parse(strs[0]);
        if (strs.length == 2) {
            this.byTime = LocalTime.parse(strs[1]);
        }
    }

    @Override
    public String storageStr() {
        return "D | " + super.getStatusValue() + " | " + super.description
                + " | " + this.byDate + (this.byTime != null ? " " + this.byTime : "");
    }

    @Override
    public String toString() {
        String result = "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (byTime != null) {
            result = result + " " + byTime.toString();
        }
        return result + ")";
    }

}
