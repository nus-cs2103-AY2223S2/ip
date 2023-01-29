package tasks;

import exceptions.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) throws DukeException {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public String getEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public String toSaveFormat() {
        return String.format("E,%s,%s,%s", this.name, this.getStartDate(), this.getEndDate());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Starts from " + this.getStartDate() + "; ends at " + this.getEndDate() + ")";
    }
}
