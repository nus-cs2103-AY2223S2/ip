package chattime.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate byDate;
    private final LocalTime byTime;

    public Deadline(String description, LocalDate bDate, LocalTime bTime) {
        super(description);
        byDate = bDate;
        byTime = bTime;
    }

    @Override
    public String toDataString() {
        return "D" + super.toDataString() + " @ " + byDate + " @ " + (byTime == null ? "0" : byTime);
    }

    @Override
    public boolean isOnDate(LocalDate time) {
        return byDate.isEqual(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + (byTime == null ? "" : byTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
