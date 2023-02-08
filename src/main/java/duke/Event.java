package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An event task.
 */
public class Event extends Task {

    protected String start;
    protected LocalDate startDate;
    protected String end;
    protected LocalDate endDate;

    /**
     * Constructs an event with given description, start and end.
     *
     * @param description Description of task.
     * @param start Time that event starts.
     * @param end Time that event ends.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
