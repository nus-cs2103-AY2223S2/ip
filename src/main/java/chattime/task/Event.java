package chattime.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;

    public Event(String description, LocalDate frDate, LocalTime frTime, LocalDate tDate, LocalTime tTime) {
        super(description);

        fromDate = frDate;
        toDate = tDate;
        fromTime = frTime;
        toTime = tTime;
    }

    @Override
    public String toDataString() {
        return "E" + super.toDataString() + " @ " + fromDate + " @ " + fromTime
                + " @ " + toDate + " @ " + toTime;
    }

    @Override
    public boolean isOnDate(LocalDate time) {
        return fromDate.isEqual(time)
                || toDate.isEqual(time)
                || (fromDate.isBefore(time) && toDate.isAfter(time));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + (fromTime == null ? "" : fromTime.format(DateTimeFormatter.ofPattern("hh:mm a ")))
                + "to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + (toTime == null ? "" : toTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
