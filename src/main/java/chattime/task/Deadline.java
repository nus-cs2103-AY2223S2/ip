package chattime.task;

import chattime.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate byDate;
    private final LocalTime byTime;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toDataString() {
        return "D" + super.toDataString() + " @ " + this.byDate + " @ " + (this.byTime == null ? "0" : this.byTime);
    }

    @Override
    public boolean onDate(LocalDate time) {
        return this.byDate.isEqual(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) +
                (this.byTime == null ? "" : this.byTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
