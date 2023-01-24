package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDate startDate;
    private LocalDate endDate;
    public Event(String task,String startDate, String endDate) throws DateTimeParseException {
        super(task);
        this.endDate = LocalDate.parse(endDate);
        this.startDate = LocalDate.parse(startDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
