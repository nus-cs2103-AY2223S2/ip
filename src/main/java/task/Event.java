package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private static final DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toText() {
        return "E" + "|" + getNameOfTask() + "|" + (isDone() ? 1 : 0) + "|" + startDate + "|" + endDate;
    }

    public LocalDate getDate() {
        return startDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + formatOfDate.format(startDate) + " to: " + formatOfDate.format(endDate) + ")";
    }
}