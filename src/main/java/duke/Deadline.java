package duke;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    String icon = "[D]";
    LocalDate date;
    LocalTime time;

    public Deadline(String details, LocalDate date, LocalTime time) {
        super(details);
        this.date = date;
        this.time = time;
    }

    public Deadline(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    public Deadline(String details) {
        super(details);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (time != null) {
            return icon + super.toString() + "(by: " + date + " " + time + ")";
        } else if (date != null) {
            return icon + super.toString() + "(by: " + date + ")";
        } else {
            return icon + super.toString();
        }
    }
}
