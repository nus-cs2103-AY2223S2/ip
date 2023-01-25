package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    LocalDateTime getBy() {
        return by;
    }

    void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (By: "
                + by.getDayOfMonth() + " " + by.getMonth() + " " + by.getYear() + " "
                + by.getHour() + by.getMinute() // bugalert: may not always be 4-digits
                + ")";
    }
}
