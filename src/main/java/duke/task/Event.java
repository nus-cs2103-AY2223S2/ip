package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDate startDate;
    protected LocalDate endDate;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        //System.out.println(start);
        //System.out.println(end);
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);

    }

    @Override
    public String toString() {
        String startDateString = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() +
                "(from: " + startDateString + " to: " + endDateString + ")";
    }
}
